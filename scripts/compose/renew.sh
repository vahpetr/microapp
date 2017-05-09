#!/bin/bash

# stop and run app

# not stop script execution if error

echo "Renew begin"

echo "Renew down"
sh scripts/compose/down.sh

echo "Renew up"
sh scripts/compose/up.sh

echo "Renew end"
