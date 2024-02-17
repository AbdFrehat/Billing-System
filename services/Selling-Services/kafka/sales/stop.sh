#!/bin/bash

docker-compose stop

projects=()
while IFS= read -r project; do
    projects+=("$project")
done < <(find "." -mindepth 2 -maxdepth 2 -type f -name deploy.sh -not -path  -exec dirname {} \;)

for project in "${projects[@]}"; do
    if [ -d "$project" ]; then
        echo "Stopping project in $project..."
        cd "$project" || exit
        ./stop.sh
        echo "Stopping complete for $project."
        echo
        cd - || exit
    else
        echo "Project directory $project not found."
    fi
done