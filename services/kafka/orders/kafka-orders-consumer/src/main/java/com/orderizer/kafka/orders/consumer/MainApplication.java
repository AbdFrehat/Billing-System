package com.orderizer.kafka.orders.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication(
        scanBasePackages = {
                "com.orderizer.kafka.orders.consumer",
                "com.orderizer.core"
        })
@EnableKafka
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
