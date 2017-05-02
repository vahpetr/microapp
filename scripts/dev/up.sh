#!/bin/bash

# stop if error
set -e

echo "Up begin"

echo "Up cluster"
sh scripts/dev/up-cluster.sh

echo "Up app"
sh scripts/dev/up-app.sh

echo "Up end"
