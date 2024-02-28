cd ../../../services/auth/auth-profiles-manager/ || exit
./gradlew clean build &&
./gradlew publishToMavenLocal &&
docker build -t aalfrihat/auth-profiles-manager:1.0.0 .

