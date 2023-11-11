package com.selling.system.source.pull.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.sale"})
public class SalesSourceMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesSourceMsApplication.class, args);
	}

}
