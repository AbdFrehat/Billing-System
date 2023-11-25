package com.selling.system.data.manager.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {
        "com.selling.system.query.manager.sales",
        "com.selling.system.shared.module"
})
public class QueryManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryManagerApplication.class, args);
    }

}
