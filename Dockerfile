FROM openjdk:18-jdk-slim

WORKDIR /app

COPY target/ToDoList-1.0-SNAPSHOT.war /app/app.war

EXPOSE 8080

CMD ["java", "-jar", "app.war"]

