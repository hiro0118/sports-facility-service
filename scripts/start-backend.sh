#!/bin/bash

# Define an array of Docker Compose projects
apis=("tennis-api")

script_dir="$(cd "$(dirname "$0")" && pwd)"

# Start API gateway
cd $script_dir/../api-gateway
docker-compose up -d

# Loop through each project and start the Docker Compose
for api in "${apis[@]}"; do
  echo "Starting $api..."
  cd $script_dir/../backend/$api
  docker-compose up -d
done
