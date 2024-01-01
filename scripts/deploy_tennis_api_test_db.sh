#!/bin/bash

# Get the directory of this script
script_dir="$(cd "$(dirname "$0")" && pwd)"

# Start DB
echo "Starting Tennis DB"
cd ${script_dir}/../backend/tennis-api/scripts/
docker-compose up tennis-test-db
