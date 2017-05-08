#!/bin/bash

# not stop script execution if error

echo "Down begin"

echo "Down app"
sh scripts/dind/down-app.sh

echo "Down cluster"
sh scripts/dind/down-cluster.sh

echo "Down end"
