Write-Host "=== Configuration du Config Server ===" -ForegroundColor Cyan

$configRSCommands = @"
rs.initiate({
  _id: "configRS",
  configsvr: true,
  members: [{ _id: 0, host: "configsvr:27019" }]
})
"@

$configRSCommands | docker exec -i configsvr mongosh --port 27019

Write-Host "Attente de l'initialisation du configRS..." -ForegroundColor Yellow
Start-Sleep -Seconds 5

Write-Host "=== Configuration du Shard Butchery ===" -ForegroundColor Cyan

$shardButcheryCommands = @"
rs.initiate({
  _id: "shardButcheryRS",
  members: [{ _id: 0, host: "shardButchery:27018" }]
})
"@

$shardButcheryCommands | docker exec -i shardButchery mongosh --port 27018

Write-Host "Attente de l'initialisation du shardButcheryRS..." -ForegroundColor Yellow
Start-Sleep -Seconds 5

Write-Host "=== Configuration du Shard Bakery ===" -ForegroundColor Cyan

$shardBakeryCommands = @"
rs.initiate({
  _id: "shardBakeryRS",
  members: [{ _id: 0, host: "shardBakery:27017" }]
})
"@

$shardBakeryCommands | docker exec -i shardBakery mongosh --port 27017

Write-Host "Attente de l'initialisation du shardBakeryRS..." -ForegroundColor Yellow
Start-Sleep -Seconds 5

Write-Host "=== Ajout des Shards au Mongos ===" -ForegroundColor Cyan

$addShardsCommands = @"
sh.addShard("shardButcheryRS/shardButchery:27018")
sh.addShard("shardBakeryRS/shardBakery:27017")
"@

$addShardsCommands | docker exec -i mongos mongosh --port 27020

Write-Host "Attente de l'ajout des shards..." -ForegroundColor Yellow
Start-Sleep -Seconds 3

Write-Host "=== Configuration du Sharding sur la base de données ===" -ForegroundColor Cyan

$shardingConfigCommands = @"
use projetnosql
db.projetnosql.createIndex({ "BUSINESS": 1 })
sh.enableSharding("projetnosql")
sh.shardCollection("projetnosql.products", { "BUSINESS": 1 })
sh.shardCollection("projetnosql.commands", { "BUSINESS": 1 })
"@

$shardingConfigCommands | docker exec -i mongos mongosh --port 27020

Write-Host "Attente de la configuration du sharding..." -ForegroundColor Yellow
Start-Sleep -Seconds 3

Write-Host "=== Distribution des chunks et création de l'utilisateur admin ===" -ForegroundColor Cyan

$finalConfigCommands = @"
sh.splitAt("projetnosql.products", { BUSINESS: "BUTCHER" })
sh.splitAt("projetnosql.products", { BUSINESS: "BAKER" })
sh.moveChunk("projetnosql.products", { BUSINESS: "BUTCHER" }, "shardButcheryRS")
sh.moveChunk("projetnosql.products", { BUSINESS: "BAKER" }, "shardBakeryRS")
use admin
db.createUser({
  user: "admin",
  pwd: "password123",
  roles: [ { role: "userAdminAnyDatabase", db: "admin" } ]
})
"@

$finalConfigCommands | docker exec -i mongos mongosh --port 27020

Write-Host "=== Configuration terminée avec succès ! ===" -ForegroundColor Green