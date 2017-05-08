#!/bin/bash

# stop if error
set -e

echo "Up begin"

echo "Up cluster"
sh scripts/vbox/up-cluster.sh

echo "Up app"
sh scripts/vbox/up-app.sh

echo "Up end"
