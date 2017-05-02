#!/bin/bash

# Stop, rebuild and run app

echo "Renew app begin"

echo "Renew app down app"
sh scripts/dev/down-app.sh

echo "Renew app up app"
sh scripts/dev/up-app.sh

echo "Renew app end"
