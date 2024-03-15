#!/bin/bash
cd ../../../../services/data/delete/data-delete-search-orders/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-delete-search-orders:1.0.0 .
