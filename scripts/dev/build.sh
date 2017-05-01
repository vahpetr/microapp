#!/bin/bash

EXEC_DIR=$PWD

echo "Build begin"

cd $EXEC_DIR

echo "Building"
mvn package

# back to script directory
cd -

echo "Build end"
