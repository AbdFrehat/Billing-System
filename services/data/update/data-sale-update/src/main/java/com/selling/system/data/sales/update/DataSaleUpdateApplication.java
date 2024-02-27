package com.selling.system.data.sales.update;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {
                "com.selling.system.data.shared.module",
                "com.selling.system.shared.module",
                "com.selling.system.data.sales.update"
        })
@EnableDiscoveryClient
public class DataSaleUpdateApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSaleUpdateApplication.class, args);
    }

}
