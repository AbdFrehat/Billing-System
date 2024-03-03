#!/bin/bash
cd ../../../../services/data/delete/data-delete-manager || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-delete-manager:1.0.0 .
