#!/bin/bash

# stop if error
set -e

echo "Up begin"

echo "Up app"
sh scripts/compose/up-app.sh

echo "Up end"
