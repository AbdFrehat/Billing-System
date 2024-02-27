#!/bin/bash

projects=(
  "auth"
  "modify"
  "source"
  "export"
  "reports"
  "kafka"
  "data"
  "shared"
)

for project in "${projects[@]}"; do
    if [ -d "$project" ]; then
        echo "Removing project in $project..."
        cd "$project" || exit
        ./down.sh
        echo "Removing complete for $project."
        echo
        cd - || exit
    else
        echo "Project directory $project not found."
    fi
done

