
FROM tomcat:9.0-jdk8-openjdk-slim

COPY target/ToDoList-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/app.war

EXPOSE 8080

CMD ["catalina.sh", "run"]

