package com.qadr.kafkaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class KafkaConsumer {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumer.class, args);
	}

}
