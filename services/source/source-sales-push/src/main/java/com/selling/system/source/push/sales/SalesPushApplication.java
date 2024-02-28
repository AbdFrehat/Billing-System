package com.selling.system.source.push.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {
        "com.selling.system.source.push.sales",
        "com.selling.system.source.random.generator.sales",
        "com.selling.system.shared.module"
})
@EnableScheduling

public class SalesPushApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesPushApplication.class, args);
    }

}
