# Dockerfile
FROM adoptopenjdk/openjdk11:jdk-11.0.10_9-alpine

VOLUME /tmp
EXPOSE 8081
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
