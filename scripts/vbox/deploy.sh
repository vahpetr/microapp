#!/bin/bash

# stop if error
set -e

echo "Deploy begin"

eval "$(docker-machine env manager1)"
docker stack deploy \
  -c docker-stack.yml \
  -c docker-stack.visualizer.yml \
  microapp

sleep 1

open http://"$(docker-machine ip manager1)":8001

echo "Deploy end"
