#!/bin/bash
cd ../../../../services/data/update/data-sales-update/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-sales-update:1.0.0 .
