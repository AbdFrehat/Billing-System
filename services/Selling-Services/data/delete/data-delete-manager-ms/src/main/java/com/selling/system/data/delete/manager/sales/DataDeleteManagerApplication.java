package com.selling.system.data.delete.manager.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication(scanBasePackages = {
        "com.selling.system.data.delete.manager.sales",
        "com.selling.system.shared.module"
})
@EnableDiscoveryClient
@EnableEurekaServer
public class DataDeleteManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataDeleteManagerApplication.class, args);
    }

}
