#!/bin/bash

# not stop script execution if error

echo "Down claster begin"

# constants
SWARM_MASTER_IP=192.168.65.2
WORKER_COUNT=2
REGISTRY="registry.local"

echo "Removing swarm worker"
for i in $(seq $WORKER_COUNT); do

  docker --host localhost:${i}2375 swarm leave
  docker rm worker-$i -f

  echo "Swarm worker-$i removed"

done

echo "Removing swarm registry"
docker rm registry -f

# save host forever
# echo "Clean host"
# sh scripts/setup-etc-hosts.sh removehost $REGISTRY $SWARM_MASTER_IP

echo "Removing swarm master"
docker swarm leave -f

echo "Running system prune"
docker system prune -f

echo "Down claster end"
