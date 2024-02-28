cd ../../../services/auth/auth-clients-manager/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/auth-clients-manager:1.0.0 .
