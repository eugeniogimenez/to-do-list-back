FROM tomcat:9.0-alpine
MAINTAINER ELG
COPY target/ToDoList-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
ENTRYPOINT ["catalina.sh", "run"]
