package com.selling.system.auth.profiles.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;


@SpringBootApplication(scanBasePackages = {
        "com.selling.system.auth.profiles.manager",
        "com.selling.system.auth.shared.module",
        "com.selling.system.shared.module",
})
@EnableDiscoveryClient
@EnableR2dbcRepositories
public class AuthProfilesManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthProfilesManagerApplication.class, args);
    }

}
