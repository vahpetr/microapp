#!/bin/bash

echo "Down begin"

echo "Down cluster"
sh scripts/dev/down-cluster.sh

echo "Up app"
sh scripts/dev/down-app.sh

echo "Down end"
