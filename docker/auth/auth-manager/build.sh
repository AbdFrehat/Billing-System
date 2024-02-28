cd ../../../services/auth/auth-manager/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/auth-manager:1.0.0 .
