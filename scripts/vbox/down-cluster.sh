#!/bin/bash

echo "Down cluster begin"

echo "WARNING: This action will stop all local machine instance."

echo "Stop all machines"
docker-machine stop $(docker-machine ls -q)

echo "Removing all machines"
docker-machine rm $(docker-machine ls -q)

echo "Down cluster end"
