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
A la racine du projet, exécuter la commande `docker compose up` pour intialiser les containers Docker.
```bash
docker compose up
```

### 2. Mise en place du sharding
Dans le répertoire `./setup`, exécuter le script de configuration du sharding de la BDD compatible avec votre OS.

- Linux/macOS : `sharding-config`
- Windows : `sharding-config.ps1`