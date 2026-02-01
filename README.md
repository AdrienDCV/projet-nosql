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

Ce repo contient également le projet frontend commun aux deux APIs développés en React/TypeScript.

## Répartition du travail
| Développeur           |                                                                                                                         Tâches                                                                                                                         |
|:----------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| DA COSTA VEIGA Adrien | - Architecture du projet<br/>- Initialisation des projets backend et frontend<br/>- Maquette UI<br/>- Génération automatique des shards<br/>- Implémentation de la logique métier des APIs `producer-api` et `client-api`<br/>- Développement frontend |
| DESERT Lorick         |                                                                                                       - Maquette UI<br/>- Développement frontend                                                                                                       |
| ROUSSELLE Nathan      |                                                                                                       - Maquette UI<br/>- Développement frontend                                                                                                       |

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
les shards. Pour des raisons de sécurité et laisser le temps au shard de se configurer, un intervale de 10 secondes est
configuré entre la création et configuration d'un shard pour assurer la configuration des shards.

```bash
# Sous UNIX/macOS
./build-shards.sh


# Sous Windows
.\DbShardingInit.ps1
# Ou si l'exécution de scripts est bloquée
powershell -ExecutionPolicy Bypass -File .\DbShardingInit.ps1 
```

### 2. Import des données
```bash
# Retour dans le répertoire producer-api/src/main/resources/scripts
cd ..

# Sous UNIX/macOS
chmod u+x ./seed-db.sh
./seed-db.sh

# Sous Windows
.\SeedDb.ps1
# Ou si l'exécution de scripts est bloquée
powershell -ExecutionPolicy Bypass -File .\SeedDb.ps1 
```

Les shards se voient affecter un port automatiquement à compter du port `27022`. Afin d'assurer une gestion correcte des reliquats, le calcul du port suivant est le suivant :
```
BASE_PORT = 27022

NEXT_PORT = BASE_PORT + le_nombre_de_businesses_en_BDD
```

**ATTENTION** : Les scripts ont été générés par IA. Bien qu'ils aient été vérifiés, corrigés et testés, ils ne sont potentiellement 
pas optimisés. Beaucoup de volumes sont créés. Bien qu'ils ne soient pas volumineux, il est préférable de les supprimer
après l'utilisation du projet.

## Variables d'envrionnement
Pour des raisons de sécurité, les variables d'environnement nécessaire à l'exécution des APIs ont été partagées par message privé sur Discord.

## Comptes utilisateurs
| Type d'utilisateur |       Identifiant        | Mot de passe |
|:-------------------|:------------------------:|:------------:|
| PRODUCER           |  emma.morel@example.com  |  azerty1234  |
| PRODUCER           | nathan.petit@example.com |  azerty1234  |
| PRODUCER           | lucas.durand@example.com |  azerty1234  |
| CLIENT             | claire.robert@example.com |  azerty1234  |
| CLIENT             | alice.martin@example.com |  azerty1234  |
| CLIENT             | thomas.bernard@example.com |  azerty1234  |


## Informations complémentaires
Nous avons rencontré des soucis d'organisation durant le développement du projet. Beaucoup de petites fonctionnalités 
supplémentaires permettant d'améliorer l'expérience utilisateur n'ont pas pu être développées ou intégrées. Pour des contraintes de temps, une importance moindre a
été portée à la qualité du code en particulier côté frontend.

L'historique des commandes côté producteurs pourrait être retravaillé pour une meilleure expérience utilisateur en groupant
les commandes par utilisateur et par date comme c'est le cas côté clients par exemple.

La gestion d'erreur est légère, la priorité a été donné à l'aspect fonctionnel. Ainsi, aucune gestion d'erreur ou presque
n'est présente côté frontend. Une gestion d'erreur plus robuste est présente côté backend.

Le projet reste néanmoins fonctionnel et a permis à l'équipe de développement de commencer à développer et/ou renforcer
les compétences en développement backend et frontend ainsi qu'en base de données distribuées.

En cas de souci, contacter : Adrien.DaCostaVeiga@uphf.fr ou par message privé Discord