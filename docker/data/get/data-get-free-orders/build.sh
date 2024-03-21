#!/bin/bash
cd ../../../../services/data/get/data-get-free-orders/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-get-free-orders:1.0.0 .
