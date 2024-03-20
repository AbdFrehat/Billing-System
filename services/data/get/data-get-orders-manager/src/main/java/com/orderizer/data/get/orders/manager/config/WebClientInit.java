package com.orderizer.data.get.orders.manager.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ConditionalOnProperty("spring.cloud.discovery.enabled")
public class WebClientInit {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}