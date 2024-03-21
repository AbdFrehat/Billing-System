#!/bin/bash
cd ../../../../services/data/update/data-update-orders/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-update-orders:1.0.0 .
