# TP Bases de Données Distribuées  

---

DA COSTA VEIGA Adrien  
DESERT Lorick  
ROUSSELLE Nathan  

FISA 4 ICy  
Semestre 7  
INSA Hauts-de-France  

---
## Introduction
Ce repo git comporte le code Java/Spring Boot des API dédiées au client (`client-api`) et aux producteurs (`producer-api`).
Les deux API se connectent à la même base de données MongoDb mais sont indépendantes l'une de l'autre.

## Instruction d\'installation

### 1. Initialisation des containers Docker 
```bash
cd producer-api/src/main/resources/scripts
```

```bash
docker compose -f docker-compose-main.yml up 
```

```bash
# Sous UNIX/macOS
./db-sharding-init

# Sous Windows
./DbShardingInit.ps1
```

Si des shards se trouvent dans le répertoire `/shards`, alors exécuter le script `build-shards.sh` pour reconstruire
les shards. Pour des raisons de sécurité et laisser le temps au shard de se configurer, un intervale de 15 secondes est
configuré entre la création et configuration d'un shard.

```bash
# Sous UNIX/macOS
./build-shards.sh

# Sous Windows
./DbShardingInit.ps1
```