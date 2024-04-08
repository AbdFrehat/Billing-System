package com.selling.system.auth.shared.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {
        "com.selling.system.auth.shared.module",
        "com.orderizer.core"
})
public class AuthSharedModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthSharedModuleApplication.class, args);
    }

}
