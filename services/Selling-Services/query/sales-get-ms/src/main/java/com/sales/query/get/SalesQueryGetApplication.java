package com.sales.query.get;

import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {
                "com.sales.query.get.controller",
                "com.sales.query.shared.models.service",
                "com.sales.query.shared.models.config",
        })
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "APIs", version = "1.0", description = "Documentation APIs v1.0"))
public class SalesQueryGetApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesQueryGetApplication.class, args);
    }

}
