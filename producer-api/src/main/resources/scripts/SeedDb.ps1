# CONFIG
$Container = "mongos"
$DB = "projetnosql"
$DataDir = ".\data"
$Port = 27020

# Liste des collections shardées
$ShardedCollections = @("products", "producerOrders")

Write-Host "Seeding database $DB via mongos container $Container"

function Import-Collection {
    param (
        [string]$Collection,
        [string]$FileName
    )

    $FilePath = Join-Path $DataDir $FileName

    if (-Not (Test-Path $FilePath)) {
        Write-Warning "Fichier $FilePath non trouvé, skipping $Collection"
        return
    }

    Write-Host "Importing $Collection ..."

    # Copier le fichier dans le conteneur
    docker cp $FilePath "$Container:/tmp/$FileName"

    # Vérifier si la collection est shardée
    $PreImportCmd = ""
    if ($ShardedCollections -contains $Collection) {
        $PreImportCmd = "db.getCollection('$Collection').deleteMany({});"
        Write-Host "La collection $Collection est shardée, suppression des documents..."
    }

    # Vider la collection si shardée
    if ($PreImportCmd -ne "") {
        docker exec -i $Container mongosh --port $Port $DB --quiet --eval $PreImportCmd
    }

    # Importer les données
    docker exec -i $Container mongoimport `
        --port $Port `
        --db $DB `
        --collection $Collection `
        --file "/tmp/$FileName" `
        --jsonArray

    Write-Host "Import of $Collection done"
    Start-Sleep -Seconds 1
}

# IMPORTS (ordre logique)
Import-Collection "producers" "producers.json"
Import-Collection "businesses" "businesses.json"
Import-Collection "products" "products.json"
Import-Collection "clients" "clients.json"
Import-Collection "carts" "carts.json"
Import-Collection "clientOrders" "clientOrders.json"
Import-Collection "producerOrders" "producerOrders.json"

Write-Host "-----------------------------------"
Write-Host "Database seeded successfully!"
