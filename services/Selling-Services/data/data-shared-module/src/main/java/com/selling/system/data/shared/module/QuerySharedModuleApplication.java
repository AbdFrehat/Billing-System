package com.selling.system.data.shared.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.selling.system.data.shared.module",
})
public class QuerySharedModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuerySharedModuleApplication.class, args);
    }

}
