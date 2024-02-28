package com.selling.system.data.sale.save;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {
                "com.selling.system.data.shared.module",
                "com.selling.system.shared.module",
                "com.selling.system.data.sale.save"
        })

public class DataSaleSaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSaleSaveApplication.class, args);
    }

}
