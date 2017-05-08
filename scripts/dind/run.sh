#!/bin/bash

# remove app, cluster and run again

# not stop script execution if error

echo "Run begin"

echo "Run down"
sh scripts/dind/down.sh

echo "Run up"
sh scripts/dind/up.sh

echo "Run end"
