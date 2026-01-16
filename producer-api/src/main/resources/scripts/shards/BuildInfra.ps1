# Arrêter l'exécution si une commande échoue
$ErrorActionPreference = "Stop"

# Récupérer tous les fichiers *_shard.yml dans le dossier courant
$shardFiles = Get-ChildItem -Path . -Filter '*_shard.yml'

foreach ($shardFile in $shardFiles) {
    Write-Host "Starting shard $($shardFile.Name)..."

    # Lancer le docker-compose pour le shard
    docker compose -f $shardFile.FullName up -d

    # Attendre 15 secondes pour laisser le shard démarrer
    Start-Sleep -Seconds 15
}