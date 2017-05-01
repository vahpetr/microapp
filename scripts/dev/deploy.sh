#!/bin/bash

EXEC_DIR=$PWD
# SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

echo "Deploy begin"

docker stack deploy \
  -c $EXEC_DIR/docker-stack.dev.yml \
  --with-registry-auth \
  microapp

echo "Deploy end"
