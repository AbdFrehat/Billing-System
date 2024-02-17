#!/bin/bash

projects=(
  "shared"
  "data"
  "kafka"
  "modify"
  "source"
  "export"
  "reports"
  "auth"
)

for project in "${projects[@]}"; do
    if [ -d "$project" ]; then
        echo "Starting project in $project..."
        cd "$project" || exit
        ./start.sh
        echo "Starting complete for $project."
        echo
        cd - || exit
    else
        echo "Project directory $project not found."
    fi
done

