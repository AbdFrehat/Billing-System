FROM openjdk:17
WORKDIR /app
COPY ./build/libs/*.jar /app/main.jar
CMD ["java", "-jar", "main.jar", "--spring.config.location=classpath:/application.yaml,classpath:/application-prod.yaml"]


