FROM amazoncorretto: 8-alpine-jdk
MAINTAINER ELG
COPY target/ToDoList-1.0-SNAPSHOT.war todolist.war
ENTRYPOINT ["java", "-war", "/todolist.war"]
