#!/bin/bash
cd ../../../../../services/data/orders/delete/data-delete-orders || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-delete-orders:1.0.0 .
