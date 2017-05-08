#!/bin/bash

# remove app, cluster and run again

# not stop script execution if error

echo "Run begin"

echo "Run down"
sh scripts/compose/down.sh

echo "Run up"
sh scripts/compose/up.sh

echo "Run end"
