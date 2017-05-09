#!/bin/bash

# not stop script execution if error

echo "Down begin"

eval "$(docker-machine env -u)"
docker-compose down

echo "Down end"
