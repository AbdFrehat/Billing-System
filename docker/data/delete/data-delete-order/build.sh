#!/bin/bash
cd ../../../../services/data/delete/data-delete-order || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-delete-order:1.0.0 .
