#!/bin/bash
cd ../../../services/source/source-push-orders/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/source-push-orders:1.0.0 .
