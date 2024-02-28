package com.selling.system.data.sales.multi.update;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;



@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {
                "com.selling.system.data.shared.module",
                "com.selling.system.shared.module",
                "com.selling.system.data.sales.multi.update"
        })

public class DataSalesUpdateApplication {


    public static void main(String[] args) {
        SpringApplication.run(DataSalesUpdateApplication.class, args);
    }

}
