package com.sales.query.get;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {
                "com.sales.query.get.controller",
                "com.sales.query.shared.models.service",
                "com.sales.query.shared.models.config"
        })
@EnableReactiveMongoRepositories
//@EnableDiscoveryClient
public class SalesQueryGetApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesQueryGetApplication.class, args);
    }

}
