package com.selling.system.data.sales.query.delete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {
                "com.selling.system.data.shared.module",
                "com.selling.system.shared.module",
                "com.selling.system.data.sales.query.delete"
        })

public class DataSaleDeleteQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSaleDeleteQueryApplication.class, args);
    }

}
