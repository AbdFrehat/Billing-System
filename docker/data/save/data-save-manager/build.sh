#!/bin/bash
cd ../../../../services/data/save/data-save-manager/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-save-manager:1.0.0 .
