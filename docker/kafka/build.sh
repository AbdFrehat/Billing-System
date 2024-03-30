#!/bin/bash

GREEN=$(tput setaf 2)
RED=$(tput setaf 1)
NORMAL=$(tput sgr0)
max_project_length=0
max_status_length=0
status=()
projects=()

while IFS= read -r project; do
    projects+=("$project")
done < <(find "." -mindepth 2 -type f -name build.sh -exec dirname {} \;)

for project in "${projects[@]}"; do
    if [ -d "$project" ]; then
        echo "Building project in $project..."
        cd "$project" || exit
        if ./build.sh; then
          status+=("Passed")
        else
          status+=("Failed")
        fi
        echo "Build complete for $project."
        echo
        cd - || exit
    else
        echo "Project directory $project not found."
    fi
done

for project in "${projects[@]}"; do
    if [ ${#project} -gt $max_project_length ]; then
        max_project_length=${#project}
    fi
done

for status in "${status[@]}"; do
    if [ ${#status} -gt $max_status_length ]; then
        max_status_length=${#status}
    fi
done

for ((i = 0; i < ${#projects[@]}; i++)); do
  if [ "${status[i]}" == "Passed" ]; then
      status_color="${GREEN}${status[i]}${NORMAL}"
  else
      status_color="${RED}${status[i]}${NORMAL}"
  fi
  printf   "| %-*s | %-*s | \n" "$max_project_length" "${projects[i]}" "$max_status_length" "$status_color"
done