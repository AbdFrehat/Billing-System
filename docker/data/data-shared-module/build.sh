#!/bin/bash
cd ../../../services/data/data-shared-module/ || exit
./gradlew clean build
./gradlew publishToMavenLocal

