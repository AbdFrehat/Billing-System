package com.selling.system.auth.users.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication(scanBasePackages = {
        "com.selling.system.auth.users.manager",
        "com.selling.system.auth.shared.module",
        "com.selling.system.shared.module"
})
@EnableDiscoveryClient
public class AuthUsersManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthUsersManagerApplication.class, args);
    }

}
