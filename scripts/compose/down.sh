#!/bin/bash

# not stop script execution if error

echo "Down begin"

echo "Down app"
sh scripts/compose/down-app.sh

echo "Down end"
