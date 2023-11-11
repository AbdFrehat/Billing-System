package com.selling.system.query.shared.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(scanBasePackages = {
        "com.selling.system.query.shared.module",
})
public class QuerySharedModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuerySharedModuleApplication.class, args);
    }

}
