#!/bin/bash

# stop if error
set -e

echo "Up begin"

echo "Up app test"
sh scripts/compose/test.sh

echo "Up app build"
sh scripts/compose/build.sh

echo "Up app deploy"
sh scripts/compose/deploy.sh

echo "Up end"
