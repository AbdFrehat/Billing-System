package com.selling.system.auth.clients.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {
        "com.selling.system.auth.clients.manager",
        "com.selling.system.auth.shared.module",
        "com.selling.system.shared.module"
})

public class AuthClientsManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthClientsManagerApplication.class, args);
    }

}
