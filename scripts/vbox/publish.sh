#!/bin/bash

# stop if error
set -e

echo "Publish begin"

echo "Publishing and building geolocation"
unset NAME
export NAME=vahpetr/geolocation
docker build -t $NAME geolocation
docker tag $NAME $NAME
docker push $NAME

echo "Publish end"
