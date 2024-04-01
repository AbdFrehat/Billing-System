#!/bin/bash
cd ../../../../../services/data/orders/get/data-get-search-orders/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-get-search-orders:1.0.0 .
