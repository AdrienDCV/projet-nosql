#!/bin/bash
set -e

for shardFile in ./*_shard.yml; do
  echo "Starting shard $shardFile..."
  docker compose -f "$shardFile" up -d
  sleep 10
done