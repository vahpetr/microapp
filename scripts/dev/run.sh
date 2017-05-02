#!/bin/bash

# remove app, cluster and run again

echo "Run begin"

echo "Run down"
sh scripts/dev/down.sh

echo "Run up"
sh scripts/dev/up.sh

echo "Run end"
