package com.selling.system.data.delete.manager.sales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientInit {

    /**
     * Creates a web client builder bean to be used by the service to call other ones.
     *
     * @return {@link WebClient.Builder}
     */
    @Bean
    
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}