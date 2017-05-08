#!/bin/bash

# stop if error
set -e

echo "Up app begin"

echo "Up app test"
sh scripts/dind/test.sh

echo "Up app build"
sh scripts/dind/build.sh

echo "Up app publish"
sh scripts/dind/publish.sh

echo "Up app deploy"
sh scripts/dind/deploy.sh

echo "Up app end"
