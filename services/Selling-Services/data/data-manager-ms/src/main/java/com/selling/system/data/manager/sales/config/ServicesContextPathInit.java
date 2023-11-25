package com.selling.system.data.manager.sales.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServicesContextPathInit {

    @Value("${config.services.context-path.data-manager-ms}")
    private String dataGetFreeMsContextPath;

    @Bean
    public Map<String, String> servicesContextPath() {
        return Map.of(
                "data-manager-ms", dataGetFreeMsContextPath
        );
    }
}
