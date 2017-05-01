#!/bin/bash

EXEC_DIR=$PWD

echo "Build begin"

cd $EXEC_DIR

echo "Cleaning target folder"
mvn clean

echo "Building"
mvn package

# back to script directory
cd -

echo "Build end"
