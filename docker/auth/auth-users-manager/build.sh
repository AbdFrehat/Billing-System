cd ../../../services/auth/auth-users-manager/ || exit
./gradlew clean build
./gradlew publishToMavenLocal
docker build -t aalfrihat/auth-users-manager:1.0.0 .
