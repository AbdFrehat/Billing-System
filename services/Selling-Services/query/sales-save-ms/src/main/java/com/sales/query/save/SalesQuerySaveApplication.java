package com.sales.query.save;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableReactiveMongoRepositories
@EnableDiscoveryClient
public class SalesQuerySaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesQuerySaveApplication.class, args);
    }

}
