package com.selling.sales.kafka.sales.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {
                "com.selling.sales.kafka.sales.consumer",
                "com.selling.system.shared.module"
        })
public class KafkaSaleConsumerMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaSaleConsumerMSApplication.class, args);
    }

}
