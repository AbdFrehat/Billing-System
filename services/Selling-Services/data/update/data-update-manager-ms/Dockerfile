FROM openjdk:17
WORKDIR /app
COPY ./build/libs/data-update-manager-ms-1.0.0-SNAPSHOT.jar /app
CMD ["java", "-jar", "data-update-manager-ms-1.0.0-SNAPSHOT.jar", "--spring.config.location=classpath:/application.yaml,classpath:/application-${SPRING_PROFILE}.yaml"]


