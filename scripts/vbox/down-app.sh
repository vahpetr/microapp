#!/bin/bash

# stop if error
set -e

echo "Down app begin"

eval "$(docker-machine env manager1)"
docker stack rm microapp

echo "Down app end"
