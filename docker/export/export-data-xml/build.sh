cd ../../../services/export/export-data-xml/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/export-data-xml:1.0.0 .