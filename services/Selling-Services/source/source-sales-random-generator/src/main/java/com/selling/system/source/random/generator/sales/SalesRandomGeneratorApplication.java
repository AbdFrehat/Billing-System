package com.selling.system.source.random.generator.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.selling.system.source.random.generator.sales"
})
public class SalesRandomGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesRandomGeneratorApplication.class, args);
    }

}
