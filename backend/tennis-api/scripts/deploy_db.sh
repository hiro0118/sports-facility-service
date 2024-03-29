#!/bin/bash

# Get the directory of this script
tennis_api_dir="$(cd "$(dirname "$0")" && pwd)/../"

# Start DB
echo "Starting Tennis DB"
cd $tennis_api_dir/scripts
docker-compose up -d tennis-test-db
