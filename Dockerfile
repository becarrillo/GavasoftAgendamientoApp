FROM openjdk:17-jdk-slim
ENV MYSQL_DBNAME=railway
ENV MYSQL_HOST=monorail.proxy.rlwy.net
ENV MYSQL_PASSWORD=wfstQjNkaYDDdqHfeUqLgphOdCnvGIbR
ENV MYSQL_PORT=29147
ENV MYSQL_USER=root
ARG AGENDAMIENTO_APP_JAR_FILE=agendamiento-app/target/agendamiento-app-0.0.1-SNAPSHOT.jar
COPY ${AGENDAMIENTO_APP_JAR_FILE} agendamiento-app.jar

EXPOSE 8083
ENTRYPOINT ["java", "-jar", "agendamiento-app.jar"]