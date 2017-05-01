#!/bin/bash

EXEC_DIR=$PWD

echo "Test begin"

cd $EXEC_DIR

echo "Resolve dependencies"
mvn dependency:resolve

echo "Verify"
mvn verify

# back to script directory
cd -

echo "Test end"
