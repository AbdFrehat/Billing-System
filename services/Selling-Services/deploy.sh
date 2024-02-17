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
        echo "Deploying project in $project..."
        cd "$project" || exit
        ./deploy.sh
        echo "Deployment complete for $project."
        echo
        cd - || exit
    else
        echo "Project directory $project not found."
    fi
done

