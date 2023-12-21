FROM openjdk:17
WORKDIR /app
COPY ./build/libs/export-data-xlsx-1.0.0-SNAPSHOT.jar /app
CMD ["java", "-jar", "export-data-xlsx-1.0.0-SNAPSHOT.jar", "--spring.config.location=classpath:/application.yaml,classpath:/application-${SPRING_PROFILE}.yaml"]


