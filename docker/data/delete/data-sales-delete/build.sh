#!/bin/bash
cd ../../../../services/data/delete/data-sales-delete || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-sales-delete:1.0.0 .
