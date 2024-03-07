cd ../../../services/export/export-data-manager/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/export-data-manager:1.0.0 .