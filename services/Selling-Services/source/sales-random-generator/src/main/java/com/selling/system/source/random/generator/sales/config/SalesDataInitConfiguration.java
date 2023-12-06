package com.selling.system.source.random.generator.sales.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selling.system.source.random.generator.sales.model.data.SalesData;
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
public class SalesDataInitConfiguration {

    private final ObjectMapper objectMapper;

    private final Resource resource;

    private final ApplicationContext context;

    public SalesDataInitConfiguration(ResourceLoader resourceLoader, ObjectMapper objectMapper, ApplicationContext context) {
        this.objectMapper = objectMapper;
        this.context = context;
        resource = resourceLoader.getResource("classpath:sales.data.json");
    }

    @Bean
    public SalesData salesData() {
        try {
            return objectMapper.readValue(resource.getInputStream(), SalesData.class);
        } catch (IOException e) {
            log.error("Unable to read sales.data.json: {}", e.getMessage());
            SpringApplication.exit(context, () -> 0);
            return null;
        }

    }
}
