#!/bin/bash

# stop if error
set -e

echo "Up app begin"

echo "Up app test"
sh scripts/vbox/test.sh

echo "Up app build"
sh scripts/vbox/build.sh

echo "Up app publish"
sh scripts/vbox/publish.sh

echo "Up app deploy"
sh scripts/vbox/deploy.sh

echo "Up app end"
