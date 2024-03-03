#!/bin/bash
cd ../../../../services/data/get/data-get-manager || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-get-manager:1.0.0 .
