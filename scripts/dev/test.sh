#!/bin/bash

# stop if error
set -e

echo "Test begin"

echo "Resolve dependencies"
mvn dependency:resolve

# run java tests
echo "Verify"
mvn verify

# add other tests here

echo "Test end"
