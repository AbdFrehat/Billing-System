#!/bin/bash
cd ../../../../../services/data/orders/get/data-get-operation-orders/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-get-operation-orders:1.0.0 .
