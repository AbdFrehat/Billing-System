package com.selling.system.kafka.sales.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.selling.system.kafka.sales.producer",
        "com.selling.system.shared.module"
})
public class KafkaSaleProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaSaleProducerApplication.class, args);
    }

}
