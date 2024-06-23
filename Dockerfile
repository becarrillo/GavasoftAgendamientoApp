FROM openjdk:17-jdk-slim
ARG AGENDAMIENTO_APP_JAR_FILE=target/agendamiento-app-0.0.1-SNAPSHOT.jar
COPY ${AGENDAMIENTO_APP_JAR_FILE} agendamiento-app.jar

EXPOSE 8083
ENTRYPOINT ["java", "-jar", "agendamiento-app.jar"]
