#!/bin/bash

# remove app, cluster and run again

# not stop script execution if error

echo "Run begin"

echo "Run down"
sh scripts/vbox/down.sh

echo "Run up"
sh scripts/vbox/up.sh

echo "Run end"
