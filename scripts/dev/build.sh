#!/bin/bash

EXEC_DIR=$PWD

echo "Build begin"

cd $EXEC_DIR

mvn dependency:resolve
mvn verify
mvn package

# back to script directory
cd -

echo "Build end"
