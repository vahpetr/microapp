#!/bin/bash

# not stop script execution if error

echo "Down begin"

echo "Down app"
sh scripts/vbox/down-app.sh

echo "Down cluster"
sh scripts/vbox/down-cluster.sh

echo "Down end"
