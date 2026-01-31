package com.fisa.producerapi.utils;

import com.fisa.producerapi.models.ShardInfo;
import com.fisa.producerapi.repositories.BusinessRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@Slf4j
@RequiredArgsConstructor
public class ShardProvider {

  private static final String SHARDS_DIR = "src/main/resources/scripts/shards";
  private static final int BASE_PORT = 27021;

  private final BusinessRepository businessRepository;

  public ShardInfo createUserShard(String businessId) {
    try {
      int port = this.getNextAvailablePort();

      // Créer le fichier docker-compose pour le shard
      String shardFileName = String.format("%s/%s_shard.yml", SHARDS_DIR, businessId);
      this.generateShardFile(businessId, port, shardFileName);

      // Démarrer le nouveau shard
      this.runDockerCompose(shardFileName, businessId);

      return new ShardInfo(businessId, port, shardFileName);
    } catch (Exception e) {
      throw new RuntimeException("Erreur lors de la création du shard", e);
    }
  }

  public Long getBusinessNumber() {
    return businessRepository.count();
  }

  public Integer getNextAvailablePort() {
    return Math.toIntExact(BASE_PORT + getBusinessNumber());
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
                      sh.splitAt(\\"%3$s.producerOrders\\", { businessId: \\"%1$s\\" });
                      sh.moveChunk(\\"%3$s.producerOrders\\", { businessId: \\"%1$s\\" }, \\"shard%1$sReplSet\\");
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

  public void runDockerCompose(String configFile, String businessId) throws IOException, InterruptedException {
    new ProcessBuilder("docker", "compose", "--project-name", businessId, "-f", configFile, "up", "-d")
            .inheritIO()
            .start()
            .waitFor();
  }

}
