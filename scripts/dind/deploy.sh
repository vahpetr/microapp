#!/bin/bash

# stop if error
set -e

echo "Deploy begin"

docker stack deploy \
  -c docker-stack.yml \
  -c docker-stack.dind.yml \
  --with-registry-auth \
  microapp

echo "Deploy end"
