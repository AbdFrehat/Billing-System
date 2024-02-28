package com.selling.system.data.get.manager.sales.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ConditionalOnProperty("spring.cloud.discovery.enabled")
public class WebClientInit {

    /**
     * Creates a web client builder bean to be used by the service to call other ones.
     *
     * @return {@link WebClient.Builder}
     */
    @Bean
    
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}