#!/bin/bash

# stop if error
set -e

REGISTRY_WITH_PORT=registry.local:5000

echo "Publish begin"

echo "Publishing images"

echo "Publishing geolocation"
docker tag microapp_geolocation $REGISTRY_WITH_PORT/geolocation
docker push $REGISTRY_WITH_PORT/geolocation

echo "Publish end"
