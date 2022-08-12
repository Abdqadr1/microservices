FROM openjdk:17.0-jdk as build

MAINTAINER abd_qadr

COPY target/kafka-consumer-1.0.jar kafka-consumer-1.0.jar

ENTRYPOINT ["java", "-jar", "kafka-consumer-1.0.jar"]