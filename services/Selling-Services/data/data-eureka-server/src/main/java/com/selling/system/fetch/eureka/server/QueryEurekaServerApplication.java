package com.selling.system.fetch.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class QueryEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryEurekaServerApplication.class, args);
	}

}
