package com.selling.system.data.save.manager.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication(scanBasePackages = {
        "com.selling.system.data.save.manager.sales",
        "com.selling.system.shared.module"
})
@EnableDiscoveryClient
@EnableEurekaServer
public class DataSaveManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSaveManagerApplication.class, args);
    }

}
