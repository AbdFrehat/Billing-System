#!/bin/bash
cd ../../../../services/data/update/data-update-manager/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-update-manager:1.0.0 .
