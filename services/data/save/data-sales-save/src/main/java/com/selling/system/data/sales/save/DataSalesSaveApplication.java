package com.selling.system.data.sales.save;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {
                "com.selling.system.data.shared.module",
                "com.selling.system.shared.module",
                "com.selling.system.data.sales.save"
        })

public class DataSalesSaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSalesSaveApplication.class, args);
    }

}
