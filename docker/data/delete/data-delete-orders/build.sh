#!/bin/bash
cd ../../../../services/data/delete/data-delete-orders || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-delete-orders:1.0.0 .
