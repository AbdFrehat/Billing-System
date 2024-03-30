package com.orderizer.data.update.orders.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {
        "com.orderizer.data.update.orders.manager",
        "com.selling.system.shared.module"
})

public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
