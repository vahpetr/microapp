#!/bin/bash

SCRIPTS_DIR=/scripts

echo "Setup permissions begin"

# http://linuxway.ru/pervye-shagi/komanda-chmod-primery-ispolzovaniya/
echo "Setup permissions(rwxr-xr-x) on all scripts files in $SCRIPTS_DIR"
chmod -R 755 $SCRIPTS_DIR

echo "Setup permissions end"
