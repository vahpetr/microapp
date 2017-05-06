#!/bin/bash

# stop if error
set -e

echo "Setup amazon env begin"

echo "Setup AWS_ACCESS_KEY_ID"
unset AWS_ACCESS_KEY_ID
AWS_ACCESS_KEY_ID=$(aws --profile default configure get aws_access_key_id)
echo "AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID" > amazon.env

echo "Setup AWS_SECRET_ACCESS_KEY"
unset AWS_SECRET_ACCESS_KEY
AWS_SECRET_ACCESS_KEY=$(aws --profile default configure get aws_secret_access_key)
echo "AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY" >> amazon.env

echo "Setup AWS_REGION"
unset AWS_REGION
AWS_REGION=$(aws --profile default configure get region)
echo "AWS_REGION=$AWS_REGION" >> amazon.env

echo "Setup amazon env end"
