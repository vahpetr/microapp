#!/bin/bash

# stop if error
set -e

echo "Up app begin"

echo "Up app build"
sh scripts/dev/build.sh

echo "Up app publish"
sh scripts/dev/publish.sh

echo "Up app deploy"
sh scripts/dev/deploy.sh

echo "Up app end"
