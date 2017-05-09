#!/bin/bash

# stop if error
set -e

echo "Deploy begin"

eval "$(docker-machine env -u)"
docker-compose up -d

sleep 5

open http://localhost:8080

echo "Deploy end"
