#!/bin/bash

docker-compose start

projects=()
while IFS= read -r project; do
    projects+=("$project")
done < <(find "." -mindepth 2 -maxdepth 2 -type f -name start.sh  -exec dirname {} \;)

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