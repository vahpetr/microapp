#!/bin/bash

# stop, rebuild and run app

# not stop script execution if error

echo "Renew app begin"

echo "Renew app down app"
sh scripts/vbox/down-app.sh

echo "Renew app up app"
sh scripts/vbox/up-app.sh

echo "Renew app end"
