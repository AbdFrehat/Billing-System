package com.selling.system.data.get.manager.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {
        "com.selling.system.data.get.manager.sales",
        "com.selling.system.shared.module"
})
public class DataGetManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataGetManagerApplication.class, args);
    }

}
