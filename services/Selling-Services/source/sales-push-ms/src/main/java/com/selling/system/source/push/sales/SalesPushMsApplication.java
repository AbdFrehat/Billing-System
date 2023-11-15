package com.selling.system.source.push.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {
        "com.selling.system.source.push.sales",
        "com.selling.system.source.random.generator.sales"
})
@EnableScheduling
@EnableDiscoveryClient
public class SalesPushMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesPushMsApplication.class, args);
    }

}
