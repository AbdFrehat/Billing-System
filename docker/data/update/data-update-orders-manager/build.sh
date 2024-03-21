#!/bin/bash
cd ../../../../services/data/update/data-update-orders-manager/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-update-orders-manager:1.0.0 .
