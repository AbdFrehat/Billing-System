cd ../../../services/export/export-data-pdf/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/export-data-pdf:1.0.0 .