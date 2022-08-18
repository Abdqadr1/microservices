FROM openjdk:17.0-jdk as build

MAINTAINER abd_qadr

COPY target/configserver-1.0.jar configserver-1.0.jar

ENTRYPOINT ["java", "-jar", "configserver-1.0.jar"]