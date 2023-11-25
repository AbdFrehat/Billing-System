package com.selling.system.data.manager.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication(scanBasePackages = {
        "com.selling.system.data.manager.sales",
        "com.selling.system.shared.module"
})
@EnableEurekaServer
public class DataManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataManagerApplication.class, args);
    }

}
