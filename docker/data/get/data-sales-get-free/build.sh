#!/bin/bash
cd ../../../../services/data/get/data-sales-get-free/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-sales-get-free:1.0.0 .
