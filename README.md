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
./dbShardingInit.ps1
```