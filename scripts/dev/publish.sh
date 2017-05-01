#!/bin/bash

EXEC_DIR=$PWD
# SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

echo "Publish begin"

echo "Building images"
docker-compose -f $EXEC_DIR/docker-compose.dev.yml build

echo "Publishing images"

echo "Publishing geolocation"
docker tag registry.local:5000/geolocation registry.local:5000/geolocation
docker push registry.local:5000/geolocation

echo "Publish end"
