package com.selling.system.source.pull.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {
        "com.selling.system.source.pull.sales",
        "com.selling.system.source.random.generator.sales",
        "com.selling.system.shared.module"
}
)

public class SalesPullSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesPullSourceApplication.class, args);
    }

}
