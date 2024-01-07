package com.selling.system.reports.generate.receipt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.selling.system.reports.generate.receipt",
		"com.selling.system.shared.module"
})
public class ReceiptGeneratorMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiptGeneratorMsApplication.class, args);
	}

}
