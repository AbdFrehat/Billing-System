package com.selling.system.auth.users.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {
        "com.selling.system.auth.users.manager",
        "com.selling.system.auth.shared.module",
        "com.orderizer.core"
})
public class AuthUsersManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthUsersManagerApplication.class, args);
    }

}
