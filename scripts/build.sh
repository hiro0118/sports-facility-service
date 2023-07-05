#!/bin/bash

# Define an array of Docker Compose projects
apis=("tennis-api")

# Get the directory of this script
script_dir="$(cd "$(dirname "$0")" && pwd)"

# Build backend APIs
echo "Starting backend APIs..."
for api in "${apis[@]}"; do
  echo "Starting $api..."
  cd $script_dir/../backend/$api
  docker-compose build
done
