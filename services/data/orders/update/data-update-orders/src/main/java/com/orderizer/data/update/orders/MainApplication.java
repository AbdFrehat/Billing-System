package com.orderizer.data.update.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;



@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {
                "com.selling.system.shared.module",
                "com.orderizer.data.update.orders"
        })

public class MainApplication {


    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
