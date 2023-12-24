#!/bin/bash

# Define an array of Docker Compose projects
apis=("tennis-api")

# Get the directory of this script
script_dir="$(cd "$(dirname "$0")" && pwd)"

# Start API gateway
echo "Starting API gateway..."
cd $script_dir/../api-gateway
docker-compose up -d

# Start backend APIs
echo "Starting backend APIs..."
for api in "${apis[@]}"; do
  echo "Starting $api..."
  cd $script_dir/../backend/$api
  docker-compose up -d
done

# Start frontend
echo "Starting frontend..."
cd $script_dir/../frontend
npm start
