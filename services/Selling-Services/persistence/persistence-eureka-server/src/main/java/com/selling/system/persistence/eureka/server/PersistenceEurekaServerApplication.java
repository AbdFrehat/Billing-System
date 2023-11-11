package com.selling.system.persistence.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PersistenceEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersistenceEurekaServerApplication.class, args);
	}

}
