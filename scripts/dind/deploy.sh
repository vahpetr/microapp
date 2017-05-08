#!/bin/bash

# stop if error
set -e

echo "Deploy begin"

eval "$(docker-machine env -u)"
docker stack deploy \
  -c docker-stack.yml \
  -c docker-stack.dind.yml \
  -c docker-stack.visualizer.yml \
  --with-registry-auth \
  microapp

sleep 1

open http://"$(docker info --format "{{.Swarm.NodeAddr}}")":8001

echo "Deploy end"
