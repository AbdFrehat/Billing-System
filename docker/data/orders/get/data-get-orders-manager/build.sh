#!/bin/bash
cd ../../../../../services/data/orders/get/data-get-orders-manager || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-get-orders-manager:1.0.0 .
