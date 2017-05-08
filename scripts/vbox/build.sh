#!/bin/bash

# stop if error
set -e

echo "Build begin"

echo "Cleaning target folder"
mvn clean

echo "Building java"
mvn package -Dmaven.test.skip=true

echo "Build end"
