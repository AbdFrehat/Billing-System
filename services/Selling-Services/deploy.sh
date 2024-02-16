#!/bin/bash

projects=()
while IFS= read -r project; do
    projects+=("$project")
done < <(find "." -mindepth 2 -type f -name deploy.sh -exec dirname {} \;)


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

