#!/bin/bash
cd ../../../../services/data/save/data-save-orders/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-save-orders:1.0.0 .
