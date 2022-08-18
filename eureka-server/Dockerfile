FROM openjdk:17.0-jdk as build

MAINTAINER abd_qadr

COPY target/eureka-server-1.0.jar eureka-server-1.0.jar

ENTRYPOINT ["java", "-jar", "eureka-server-1.0.jar"]