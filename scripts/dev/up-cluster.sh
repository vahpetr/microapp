#!/bin/bash

# stop if error
set -e

echo "Up cluster begin"

# constants
PUBLIC_IP=192.168.65.2
INTERNAL_IP=0.0.0.0
WORKER_COUNT=2
REGISTRY_NAME=registry.local
SWARM_MASTER_PORT=2377
REGISTRY_PORT=5000

echo "Creating swarm master"
docker swarm init --advertise-addr=$PUBLIC_IP --listen-addr=$INTERNAL_IP:$SWARM_MASTER_PORT

echo "Get swarm token"
SWARM_TOKEN=$(docker swarm join-token -q worker)
echo "SWARM_TOKEN=$SWARM_TOKEN"

# echo "Get swarm master IP"
# PUBLIC_IP=$(docker info --format "{{.Swarm.NodeAddr}}")
# echo "PUBLIC_IP=$PUBLIC_IP"

echo "Start swarm registry"

echo "Setup host"
sh scripts/setup-etc-hosts.sh addhost $REGISTRY_NAME $PUBLIC_IP

sleep 1

docker run -d --hostname=$REGISTRY_NAME -p $REGISTRY_PORT:5000 --restart=always --name registry \
  -v $PWD/.registry-local:/var/lib/registry registry:2.6.1

echo "Starting swarm workers"
for i in $(seq $WORKER_COUNT); do

  sleep 1.0e-1

  # https://docs.docker.com/swarm/plan-for-production/#network-access-control
  # https://docs.docker.com/get-started/part4/#accessing-your-cluster
  echo "Starting dind swarm worker-$i"
  docker run -d --privileged --restart=always --name worker-${i} -h worker-${i} \
    -p ${i}2375:2375 \
    docker:17.04.0-ce-dind \
    --storage-driver=overlay2 \
    --insecure-registry=$REGISTRY_NAME:$REGISTRY_PORT \
    --registry-mirror=http://$REGISTRY_NAME:$REGISTRY_PORT \
    --registry-mirror=https://$REGISTRY_NAME:$REGISTRY_PORT

  sleep 1.0e-1

  echo "Join dind swarm worker-$i to swarm master"
  docker -H :${i}2375 swarm join --token $SWARM_TOKEN $PUBLIC_IP:$SWARM_MASTER_PORT

done

sleep 1.0e-1

echo "Swarm cluster:"
docker node ls

echo "Up cluster end"
