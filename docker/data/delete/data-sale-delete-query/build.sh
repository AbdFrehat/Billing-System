#!/bin/bash
cd ../../../../services/data/delete/data-sale-delete-query/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-sales-delete-query:1.0.0 .
