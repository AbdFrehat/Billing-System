#!/bin/bash
cd ../../../../services/data/update/data-sale-update/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-sale-update:1.0.0 .
