#!/bin/bash
cd ../../../../services/data/delete/data-sale-delete || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-sale-delete:1.0.0 .
