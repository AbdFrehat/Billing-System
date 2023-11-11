package com.selling.system.shared.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.selling.system.shared.module")
public class SharedModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharedModuleApplication.class, args);
    }

}
