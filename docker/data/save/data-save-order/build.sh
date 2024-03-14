#!/bin/bash
cd ../../../../services/data/save/data-save-order/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/data-save-order:1.0.0 .
