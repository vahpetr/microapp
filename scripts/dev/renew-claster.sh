#!/bin/bash

# Down and up cluster

echo "Renew cluster begin"

echo "Renew cluster down cluster"
sh scripts/dev/down-cluster.sh

echo "Renew cluster up cluster"
sh scripts/dev/up-cluster.sh

echo "Renew cluster end"
