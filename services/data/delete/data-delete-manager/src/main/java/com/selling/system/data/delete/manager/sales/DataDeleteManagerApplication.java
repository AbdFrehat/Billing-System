package com.selling.system.data.delete.manager.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {
        "com.selling.system.data.delete.manager.sales",
        "com.selling.system.shared.module"
})

public class DataDeleteManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataDeleteManagerApplication.class, args);
    }

}
