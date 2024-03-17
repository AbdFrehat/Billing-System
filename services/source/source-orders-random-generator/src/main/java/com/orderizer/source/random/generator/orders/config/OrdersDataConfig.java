package com.orderizer.source.random.generator.orders.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderizer.source.random.generator.orders.model.data.OrdersData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

@Configuration
@Slf4j
public class OrdersDataConfig {

    private final ObjectMapper objectMapper;

    private final Resource resource;

    private final ApplicationContext context;

    public OrdersDataConfig(ResourceLoader resourceLoader, ObjectMapper objectMapper, ApplicationContext context) {
        this.objectMapper = objectMapper;
        this.context = context;
        resource = resourceLoader.getResource("classpath:sales.data.json");
    }

    @Bean
    public OrdersData ordersData() {
        try {
            return objectMapper.readValue(resource.getInputStream(), OrdersData.class);
        } catch (IOException e) {
            log.error("Unable to read sales.data.json: {}", e.getMessage());
            SpringApplication.exit(context, () -> 0);
            return null;
        }

    }
}
