#!/bin/bash

# stop if error
set -e

echo "Deploy begin"

eval "$(docker-machine env manager1)"

export SWARM_HOST=tcp://$(docker-machine ip manager1):2376

docker stack deploy \
  -c docker-stack.yml \
  microapp

sleep 3

open http://"$(docker-machine ip manager1)":8001

echo "Deploy end"
