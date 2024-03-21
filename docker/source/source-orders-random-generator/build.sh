#!/bin/bash
cd ../../../services/source/source-orders-random-generator/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
