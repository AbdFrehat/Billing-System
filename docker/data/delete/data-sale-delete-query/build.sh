#!/bin/bash
cd ../../../../services/data/delete/data-sale-delete-query/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-sale-delete-query:1.0.0 .
