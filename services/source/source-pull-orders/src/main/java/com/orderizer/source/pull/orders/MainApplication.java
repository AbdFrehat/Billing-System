package com.orderizer.source.pull.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {
        "com.orderizer.source.pull.orders",
        "com.orderizer.source.random.generator.orders",
        "com.selling.system.shared.module"
}
)

public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
