package com.selling.system.modify.sales.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.selling.system.modify.sales.manager",
		"com.selling.system.shared.module"
})
public class ModifySalesManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModifySalesManagerApplication.class, args);
	}

}
