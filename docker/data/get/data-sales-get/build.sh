#!/bin/bash
cd ../../../../services/data/get/data-sales-get/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-sales-get:1.0.0 .
