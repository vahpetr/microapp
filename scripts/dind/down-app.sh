#!/bin/bash

# stop if error
set -e

echo "Down app begin"

docker stack rm microapp

echo "Down app end"
