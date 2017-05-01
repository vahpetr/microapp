#!/bin/bash

EXEC_DIR=$PWD

echo "Test begin"

cd $EXEC_DIR

echo "Resolve dependencies"
mvn dependency:resolve

# run java tests
echo "Verify"
mvn verify

# add other tests here

# back to script directory
cd -

echo "Test end"
