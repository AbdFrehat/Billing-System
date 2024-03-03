#!/bin/bash
cd ../../../../services/data/save/data-sales-save/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-sales-save:1.0.0 .
