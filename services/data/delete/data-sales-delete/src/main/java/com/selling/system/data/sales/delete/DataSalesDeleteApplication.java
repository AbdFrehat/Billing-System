package com.selling.system.data.sales.delete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {
                "com.selling.system.data.shared.module",
                "com.selling.system.shared.module",
                "com.selling.system.data.sales.delete"
        })

public class DataSalesDeleteApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSalesDeleteApplication.class, args);
    }

}
