#!/bin/bash

# stop if error
set -e

echo "Test begin"

echo "Resolve dependencies"
mvn dependency:resolve

# run java tests
echo "Verify"
mvn verify

echo "Validate docker-compose"
docker-compose config

# add other tests here

echo "Test end"
