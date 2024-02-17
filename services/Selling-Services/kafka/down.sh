#!/bin/bash

docker-compose down

projects=()
while IFS= read -r project; do
    projects+=("$project")
done < <(find "." -mindepth 2 -maxdepth 2 -type f -name down.sh -exec dirname {} \;)

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