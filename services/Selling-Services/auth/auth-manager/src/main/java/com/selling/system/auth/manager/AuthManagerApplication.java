package com.selling.system.auth.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication(scanBasePackages = {
        "com.selling.system.auth.manager",
        "com.selling.system.shared.module"
}, exclude = DataSourceAutoConfiguration.class)
@ConfigurationPropertiesScan("com.selling.system.auth.manager.config")
@EnableDiscoveryClient
public class AuthManagerApplication {


    public static void main(String[] args) {
        SpringApplication.run(AuthManagerApplication.class, args);
    }


}
