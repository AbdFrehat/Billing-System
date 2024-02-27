package com.selling.system.modify.delete.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.selling.system.modify.delete.sales",
		"com.selling.system.modify.shared.sales",
		"com.selling.system.shared.module"
})
public class ModifyDeleteSalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModifyDeleteSalesApplication.class, args);
	}

}
