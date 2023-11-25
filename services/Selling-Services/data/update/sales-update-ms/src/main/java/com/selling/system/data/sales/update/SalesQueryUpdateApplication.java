package com.selling.system.data.sales.update;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {
                "com.selling.system.query.shared.module",
                "com.selling.system.shared.module",
                "com.selling.system.query.sales.update"
        })
@EnableDiscoveryClient
public class SalesQueryUpdateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesQueryUpdateApplication.class, args);
    }

}
