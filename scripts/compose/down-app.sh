#!/bin/bash

# stop if error
set -e

echo "Down app begin"

eval "$(docker-machine env -u)"
docker-compose down

echo "Down app end"
