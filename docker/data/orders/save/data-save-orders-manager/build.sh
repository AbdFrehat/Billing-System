#!/bin/bash
cd ../../../../../services/data/orders/save/data-save-orders-manager/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-save-orders-manager:1.0.0 .
