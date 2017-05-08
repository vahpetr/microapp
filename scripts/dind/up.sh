#!/bin/bash

# stop if error
set -e

echo "Up begin"

echo "Up cluster"
sh scripts/dind/up-cluster.sh

echo "Up app"
sh scripts/dind/up-app.sh

echo "Up end"
