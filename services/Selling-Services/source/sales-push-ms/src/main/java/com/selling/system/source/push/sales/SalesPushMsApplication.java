package com.sale.source.sales.push.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {
        "com.sale.source.sales.push.ms",
        "com.sale.source.sales.random.generator"
})
@EnableScheduling
@EnableDiscoveryClient
public class SalesPushMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesPushMsApplication.class, args);
    }

}
