#!/bin/bash

projects=()

while IFS= read -r project; do
    projects+=("$project")
done < <(find "." -type f -name build.gradle.kts -exec dirname {} \;)

for project in "${projects[@]}"; do
    if [ -d "$project" ]; then
        echo "Building project in $project..."
        cd "$project" || exit
        ./gradlew clean build
        echo "Build complete for $project."
        echo
        cd - || exit
    else
        echo "Project directory $project not found."
    fi
done

