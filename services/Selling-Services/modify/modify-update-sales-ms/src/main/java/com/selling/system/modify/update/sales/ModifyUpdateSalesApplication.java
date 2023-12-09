package com.selling.system.modify.update.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.selling.system.modify.shared.sales",
		"com.selling.system.modify.update.sales"
})
public class ModifyUpdateSalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModifyUpdateSalesApplication.class, args);
	}

}
