package com.selling.system.data.update.manager.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {
        "com.selling.system.data.update.manager.sales",
        "com.selling.system.shared.module"
})

public class DataUpdateManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataUpdateManagerApplication.class, args);
    }

}
