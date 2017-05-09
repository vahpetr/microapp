#!/bin/bash

# stop if error
set -e

REGISTRY_WITH_PORT=registry.local:5000

echo "Publish begin"

echo "Prepare geolocation"
export NAME=$REGISTRY_WITH_PORT/geolocation
docker build -t $NAME geolocation
docker tag $NAME $NAME
docker push $NAME

echo "Prepare reverseproxy"
export NAME=$REGISTRY_WITH_PORT/reverseproxy
docker build -t $NAME reverseproxy
docker tag $NAME $NAME
docker push $NAME

echo "Publish end"
