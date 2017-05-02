#!/bin/bash

# stop if error
set -e

echo "Up cluster begin"

# constants
SWARM_MASTER_IP=192.168.65.2
WORKER_COUNT=2
REGISTRY="registry.local"
SWARM_MASTER_PORT=2377
REGISTRY_PORT=5000

echo "Creating swarm master"
docker swarm init --advertise-addr=$SWARM_MASTER_IP:$SWARM_MASTER_IP --listen-addr=$SWARM_MASTER_IP:$SWARM_MASTER_PORT

echo "Get swarm token"
SWARM_TOKEN=$(docker swarm join-token -q worker)
echo "SWARM_TOKEN=$SWARM_TOKEN"

# for example if SWARM_MASTER_IP not set before
# echo "Get swarm master IP"
# SWARM_MASTER_IP=$(docker info --format "{{.Swarm.NodeAddr}}")
# echo "SWARM_MASTER_IP=$SWARM_MASTER_IP"

echo "Start swarm registry"

# need add secure
# mkdir -p certs && openssl req \
#   -newkey rsa:4096 -nodes -sha256 -keyout certs/domain.key \
#   -x509 -days 365 -out certs/domain.crt

echo "Setup host"
sh scripts/manage-etc-hosts.sh addhost $REGISTRY $SWARM_MASTER_IP

sleep 1

#   -e REGISTRY_PROXY_REMOTEURL=https://registry-1.docker.io \
docker run -d --hostname=$REGISTRY -p $REGISTRY_PORT:5000 --restart=always --name registry \
  -v $PWD/.registry-local:/var/lib/registry registry:2.6.1

echo "Starting swarm workers"
for i in $(seq $WORKER_COUNT); do

  sleep 1.0e-1

  # https://docs.docker.com/swarm/plan-for-production/#network-access-control
  echo "Starting dind swarm worker-$i"
  docker run -d --privileged --restart=always --name worker-${i} -h worker-${i} \
    -p ${i}2375:2375 \
    docker:17.04.0-ce-dind \
    --storage-driver=overlay2 \
    --insecure-registry=$REGISTRY:$REGISTRY_PORT \
    --registry-mirror=http://$REGISTRY:$REGISTRY_PORT \
    --registry-mirror=https://$REGISTRY:$REGISTRY_PORT

  sleep 1.0e-1

  echo "Join dind swarm worker-$i to swarm master"
  docker --host=localhost:${i}2375 swarm join --token $SWARM_TOKEN $SWARM_MASTER_IP:$SWARM_MASTER_PORT

done

sleep 1.0e-1

echo "Swarm cluster:"
docker node ls

echo "Start swarm visualizer"
docker run -it -d --name visualizer --restart=always -p 8080:8080 -e HOST=localhost \
  -v /var/run/docker.sock:/var/run/docker.sock dockersamples/visualizer:stable

sleep 1

echo "Open swarm visualizer"
open "http://localhost:8080"

echo "Up cluster end"
