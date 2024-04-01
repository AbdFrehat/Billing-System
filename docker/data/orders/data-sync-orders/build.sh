#!/bin/bash
cd ../../../../services/data/orders/data-sync-orders || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-sync-orders:1.0.0 .
