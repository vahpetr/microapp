#!/bin/bash

# stop if error
set -e

echo "Publish begin"

echo "Prepare geolocation"
export NAME=vahpetr/geolocation
docker build -t $NAME geolocation
docker tag $NAME $NAME
docker push $NAME

echo "Prepare reverseproxy"
export NAME=vahpetr/reverseproxy
docker build -t $NAME reverseproxy
docker tag $NAME $NAME
docker push $NAME
