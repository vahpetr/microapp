#!/bin/bash

# stop if error
set -e

REGISTRY_WITH_PORT=registry.local:5000

echo "Publish begin"

echo "Building images"
docker-compose --file docker-compose.yml --project-name microapp --verbose build

echo "Publishing images"

echo "Publishing geolocation"
docker tag microapp_geolocation $REGISTRY_WITH_PORT/geolocation
docker push $REGISTRY_WITH_PORT/geolocation

echo "Publish end"
