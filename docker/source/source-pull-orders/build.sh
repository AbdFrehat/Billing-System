#!/bin/bash
cd ../../../services/source/source-pull-orders/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/source-pull-orders:1.0.0 .
