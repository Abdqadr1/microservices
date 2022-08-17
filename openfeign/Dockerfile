FROM openjdk:17.0-jdk as build

MAINTAINER abd_qadr

COPY target/openfeign-1.0.jar openfeign-1.0.jar

ENTRYPOINT ["java", "-jar", "openfeign-1.0.jar"]