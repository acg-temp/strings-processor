
FROM tomcat:9.0.58-jdk17-openjdk-slim

COPY  /target/strings-processor.war /usr/local/tomcat/webapps/strings-processor.war



CMD ["catalina.sh", "run"]