FROM openjdk:17
MAINTAINER alanfigueroa
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]