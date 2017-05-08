#!/bin/bash

# down and up cluster

# not stop script execution if error

echo "Renew cluster begin"

echo "Renew cluster down cluster"
sh scripts/vbox/down-cluster.sh

echo "Renew cluster up cluster"
sh scripts/vbox/up-cluster.sh

echo "Renew cluster end"
