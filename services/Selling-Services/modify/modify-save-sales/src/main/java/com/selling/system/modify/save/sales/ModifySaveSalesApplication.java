package com.selling.system.modify.save.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.selling.system.modify.shared.sales",
		"com.selling.system.modify.save.sales"
})
public class ModifySaveSalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModifySaveSalesApplication.class, args);
	}

}
