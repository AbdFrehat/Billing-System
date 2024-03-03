#!/bin/bash
cd ../../../../services/data/get/data-sales-get-opt/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-sales-get-opt:1.0.0 .
