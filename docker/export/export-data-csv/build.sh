cd ../../../services/export/export-data-csv/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/export-data-csv:1.0.0 .