package com.selling.system.kafka.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class KafkaEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaEurekaServerApplication.class, args);
	}

}
