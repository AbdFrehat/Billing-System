cd ../../../services/export/export-data-xlsx/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/export-data-xlsx:1.0.0 .