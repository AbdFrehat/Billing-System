package com.selling.system.shared.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "com.selling.system.shared.module")
@ConfigurationPropertiesScan(basePackages = "com.selling.system.shared.module.config")
public class SharedModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharedModuleApplication.class, args);
    }

}
