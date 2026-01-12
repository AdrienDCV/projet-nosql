package com.fisa.producerapi.utils;

import com.fisa.producerapi.models.ShardInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@Slf4j
public class ShardProvider {

  private static final String SHARDS_DIR = "src/main/resources/scripts/shards";
  private static final String MAIN_COMPOSE_FILE = "src/main/resources/scripts/docker-compose-main.yml";
  private static final int BASE_PORT = 27021;

  public ShardInfo createUserShard(String businessId) {
    try {
      int port = this.getNextAvailablePort();

      // Créer le fichier docker-compose pour le shard
      String shardFileName = String.format("%s/%s_shard.yml", SHARDS_DIR, businessId);
      this.generateShardFile(businessId, port, shardFileName);

      // Mettre à jour le fichier principal
      this.updateMainCompose(businessId);

      // Démarrer le nouveau shard
      this.runDockerCompose(shardFileName, businessId);

      return new ShardInfo(businessId, port, shardFileName);
    } catch (Exception e) {
      throw new RuntimeException("Erreur lors de la création du shard", e);
    }
  }

  public int getNextAvailablePort() throws IOException {
    try (Stream<Path> paths = Files.list(Paths.get(SHARDS_DIR))) {
      List<Integer> usedPorts = paths.filter(path -> path.toString().endsWith("_shard.yml"))
              .map(this::extractPortFromCompose)
              .flatMap(Optional::stream)
              .toList();

      return usedPorts.isEmpty() ? BASE_PORT : Collections.max(usedPorts) + 1;
    }
  }

  private Optional<Integer> extractPortFromCompose(Path path) {
    try {
      String content = Files.readString(path);
      return Arrays.stream(content.split("\n"))
              .filter(line -> line.trim().startsWith("- \"") && line.contains(":"))
              .map(line -> line.trim().replace("- \"", "").replace("\"", "").split(":")[0])
              .map(Integer::parseInt)
              .findFirst();
    } catch (Exception e) {
      log.error("Erreur de lecture du port dans {}", path, e);
      return Optional.empty();
    }
  }

  public void generateShardFile(String businessId, int port, String filename) throws IOException {
    String dbName = "projetnosql";
    String template = """
            services:
              shard-%1$s:
                image: mongo:7
                container_name: shard-%1$s
                networks:
                  - app-network
                command: mongod --shardsvr --replSet shard%1$sReplSet --port %2$d --bind_ip_all
                ports:
                  - "%2$d:%2$d"
                volumes:
                  - shard-%1$s-data:/data/db
            
              shard-%1$s-init:
                image: mongo:7
                container_name: shard-%1$s-init
                depends_on:
                  - shard-%1$s
                networks:
                  - app-network
                entrypoint: >
                  bash -c "
                    sleep 15 &&
                    mongosh --host shard-%1$s:%2$d --eval '
                      rs.initiate({
                        _id: \\"shard%1$sReplSet\\",
                        members: [{ _id: 0, host: \\"shard-%1$s:%2$d\\" }]
                      })
                    ' &&
                    sleep 15 &&
                    mongosh --host mongos:27020 --eval '
                      sh.addShard(\\"shard%1$sReplSet/shard-%1$s:%2$d\\");
                      sh.splitAt(\\"%3$s.products\\", { businessId: \\"%1$s\\" });
                      sh.moveChunk(\\"%3$s.products\\", { businessId: \\"%1$s\\" }, \\"shard%1$sReplSet\\");
                      sh.splitAt(\\"%3$s.orders\\", { businessId: \\"%1$s\\" });
                      sh.moveChunk(\\"%3$s.orders\\", { businessId: \\"%1$s\\" }, \\"shard%1$sReplSet\\");
                      print(\\"Shard configured for business: %1$s\\");
                    '
                  "
                restart: "no"
            
            volumes:
              shard-%1$s-data: {}
            
            networks:
              app-network:
                external: true
                name: scripts_default
                """.formatted(businessId, port, dbName);

    Files.writeString(Paths.get(filename), template);
  }

  public void updateMainCompose(String userId) throws IOException {
    Yaml yaml = new Yaml();
    Path path = Paths.get(MAIN_COMPOSE_FILE);
    Map<String, Object> oldData = Files.exists(path) ? yaml.load(Files.readString(path)) : new LinkedHashMap<>();
    if (oldData == null) oldData = new LinkedHashMap<>();

    List<Object> includes = (oldData.get("include") instanceof List<?> list) ? new ArrayList<>(list) : new ArrayList<>();
    String shardPath = String.format("./shards/%s_shard.yml", userId);

    boolean alreadyIncluded = includes.stream().anyMatch(inc ->
            (inc instanceof Map<?, ?> m) ? shardPath.equals(m.get("path")) : shardPath.equals(inc)
    );

    if (!alreadyIncluded) {
      includes.add(Map.of("path", shardPath));
    }

    Map<String, Object> composeData = new LinkedHashMap<>();
    composeData.put("include", includes);

    Map<String, Object> finalOldData = oldData;
    List.of("services", "networks", "volumes").forEach(key -> {
      if (finalOldData.containsKey(key)) composeData.put(key, finalOldData.get(key));
    });

    DumperOptions options = new DumperOptions();
    options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    options.setPrettyFlow(true);
    options.setIndent(2);

    try (FileWriter writer = new FileWriter(MAIN_COMPOSE_FILE)) {
      new Yaml(options).dump(composeData, writer);
    }
  }

  public void runDockerCompose(String configFile, String businessId) throws IOException, InterruptedException {
    new ProcessBuilder("docker", "compose", "--project-name", businessId, "-f", configFile, "up", "-d")
            .inheritIO()
            .start()
            .waitFor();
  }

}
