cd ../../../services/export/export-data-shared-module/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/export-data-shared-module:1.0.0 .