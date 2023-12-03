FROM openjdk:17
ENV SPRING_PROFILE=dev
WORKDIR /app
COPY ./build/libs/data-delete-manager-ms-1.0.0-SNAPSHOT.jar /app
CMD ["java", "-jar", "data-delete-manager-ms-1.0.0-SNAPSHOT.jar", "--spring.config.location=classpath:/application.yaml,classpath:/application-${SPRING_PROFILE}.yaml"]


