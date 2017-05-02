#!/bin/bash

EXEC_DIR=$PWD
# SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

REGISTRY_WITH_PORT=registry.local:5000

echo "Publish begin"

echo "Building images"
docker-compose -f $EXEC_DIR/docker-compose.dev.yml build

echo "Publishing images"

echo "Publishing geolocation"
docker tag $REGISTRY_WITH_PORT/geolocation $REGISTRY_WITH_PORT/geolocation
docker push $REGISTRY_WITH_PORT/geolocation

echo "Publish end"
