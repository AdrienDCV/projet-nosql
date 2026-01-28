#################################
# CONFIG SERVER REPLICA SET
#################################
Write-Host "=== [1/4] Init Config Server Replica Set ==="

$script = @"
rs.initiate({
  _id: "configRS",
  configsvr: true,
  members: [
    { _id: 0, host: "configsvr:27019" }
  ]
})
"@
$script | Set-Content -Encoding UTF8 config.js
docker cp config.js configsvr:/config.js
docker exec configsvr mongosh --port 27019 /config.js
Remove-Item config.js

Start-Sleep -Seconds 8

#################################
# CORE SHARD REPLICA SET
#################################
Write-Host "=== [2/4] Init Core Shard Replica Set ==="

$script = @"
rs.initiate({
  _id: "coreRS",
  members: [
    { _id: 0, host: "coreshard:27018" }
  ]
})
"@
$script | Set-Content -Encoding UTF8 core.js
docker cp core.js coreshard:/core.js
docker exec coreshard mongosh --port 27018 /core.js
Remove-Item core.js

Start-Sleep -Seconds 8

#################################
# CLUSTER WIRING (mongos)
#################################
Write-Host "=== [3/4] Cluster wiring (add shard) ==="

$script = @"
sh.addShard("coreRS/coreshard:27018")
"@
$script | Set-Content -Encoding UTF8 shard.js
docker cp shard.js mongos:/shard.js
docker exec mongos mongosh --port 27020 /shard.js
Remove-Item shard.js

Start-Sleep -Seconds 3

#################################
# SHARDING LOGIQUE
#################################
Write-Host "=== [4/4] Enable sharding and shard collections ==="

$script = @"
// Activer le sharding sur la base
sh.enableSharding("projetnosql")

// Index de shard
db.getSiblingDB("projetnosql").products.createIndex({ businessId: 1 })
db.getSiblingDB("projetnosql").orders.createIndex({ businessId: 1 })

// Sharding des collections
sh.shardCollection("projetnosql.products", { businessId: 1 })
sh.shardCollection("projetnosql.orders", { businessId: 1 })
"@
$script | Set-Content -Encoding UTF8 logic.js
docker cp logic.js mongos:/logic.js
docker exec mongos mongosh --port 27020 /logic.js
Remove-Item logic.js

Start-Sleep -Seconds 3

#################################
# ADMIN USER
#################################
Write-Host "=== [SECURITY] Create admin user ==="

$script = @"
db.getSiblingDB("admin").createUser({
  user: "admin",
  pwd: "password123",
  roles: [
    { role: "userAdminAnyDatabase", db: "admin" },
    { role: "clusterAdmin", db: "admin" }
  ]
})
"@
$script | Set-Content -Encoding UTF8 admin.js
docker cp admin.js mongos:/admin.js
docker exec mongos mongosh --port 27020 /admin.js
Remove-Item admin.js

Write-Host "=== ✅ MongoDB sharded cluster ready ==="