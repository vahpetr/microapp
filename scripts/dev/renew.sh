#!/bin/bash

# Stop, rebuild and run

echo "Renew begin"

echo "Renew down app"
sh scripts/dev/down-app.sh

echo "Renew up app"
sh scripts/dev/up-app.sh

echo "Renew end"
