package com.selling.sales.kafka.sales.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableReactiveMongoRepositories
public class KafkaSaleConsumerMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaSaleConsumerMSApplication.class, args);
    }

}
