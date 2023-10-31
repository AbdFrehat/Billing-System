package com.sale.persistence.kafka.sale.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfiguration {

    @Value("${spring.kafka.template.default-topic}")
    private String defaultTopicName;

    @Value("${spring.kafka.template.partitions}")

    private int partitionsNumber;

    @Value("${spring.kafka.template.replicas}")
    private int replicasNumber;
    @Bean
    public NewTopic defaultTopic() {
        return TopicBuilder
                .name(defaultTopicName)
                .partitions(partitionsNumber)
                .replicas(replicasNumber)
                .build();
    }


}
