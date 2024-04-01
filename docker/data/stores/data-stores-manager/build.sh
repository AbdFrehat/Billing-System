#!/bin/bash
cd ../../../../services/data/stores/data-stores-manager/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-stores-manager:1.0.0 .
