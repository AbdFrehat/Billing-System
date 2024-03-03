#!/bin/bash
cd ../../../../services/data/save/data-sale-save/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-sale-save:1.0.0 .
