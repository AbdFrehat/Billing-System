#!/bin/bash
cd ../../../services/data/data-manager/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-manager:1.0.0 .
