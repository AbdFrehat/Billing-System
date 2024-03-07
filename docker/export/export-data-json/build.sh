cd ../../../services/export/export-data-json/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/export-data-json:1.0.0 .