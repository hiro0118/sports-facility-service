#!/bin/bash

api="tennis-api"

# Get backend directory
backend_dir="$(cd "$(dirname "$0")" && pwd)/../backend"

# Stop all the containers
dockers_to_stop="$(docker ps -aq --filter "status=running")"
if [ -n "$dockers_to_stop" ]; then
  echo "Stopping Containers"
  docker stop ${dockers_to_stop}
fi

# Start DB container
echo "Starting DB"
cd ${backend_dir}/${api}
./scripts/deploy_db.sh

# Run integration tests
echo "Running integration tests"
cd ${backend_dir}/${api}
./gradlew integration -i

# Stop all the containers
echo "Stopping Containers"
docker stop $(docker ps -aq --filter "status=running")
