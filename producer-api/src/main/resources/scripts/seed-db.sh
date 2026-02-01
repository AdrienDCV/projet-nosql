#!/bin/bash
set -e

# CONFIG
CONTAINER="mongos"
DB="projetnosql"
DATA_DIR="./data"
PORT="27020"

# Liste des collections shardées
SHARDED_COLLECTIONS=("products" "producerOrders")

echo "Seeding database $DB via mongos container $CONTAINER"

# Fonction d'import
import_collection () {
  local collection=$1
  local file="$DATA_DIR/$2"

  if [ ! -f "$file" ]; then
    echo "Fichier $file non trouvé, skipping $collection"
    return
  fi

  echo "Importing $collection ..."

  # Copier le fichier dans le conteneur
  docker cp "$file" "$CONTAINER:/tmp/$2"

  # Si la collection est shardée, vider avec deleteMany, sinon drop
  local pre_import_cmd=""
  for shard in "${SHARDED_COLLECTIONS[@]}"; do
    if [ "$collection" == "$shard" ]; then
      pre_import_cmd="db.getCollection('$collection').deleteMany({});"
      echo "La collection $collection est shardée, suppression des documents..."
      break
    fi
  done

  # Lancer mongoimport dans le conteneur
  docker exec -i $CONTAINER mongosh --port $PORT $DB --quiet <<EOF
// Vider la collection si shardée
$pre_import_cmd
EOF

  # Importer les données
  docker exec -i $CONTAINER mongoimport \
    --port $PORT \
    --db "$DB" \
    --collection "$collection" \
    --file "/tmp/$2" \
    --jsonArray

  echo "Import of $collection done"
  sleep 1
}

# IMPORTS (ordre logique)
import_collection "producers" "producers.json"
import_collection "businesses" "businesses.json"
import_collection "products" "products.json"
import_collection "clients" "clients.json"
import_collection "carts" "carts.json"
import_collection "clientOrders" "clientOrders.json"
import_collection "producerOrders" "producerOrders.json"

echo "-----------------------------------"
echo "Database seeded successfully!"
