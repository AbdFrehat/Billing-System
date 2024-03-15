#!/bin/bash
cd ../../../../services/data/delete/data-delete-orders-manager || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-delete-orders-manager:1.0.0 .
