package com.selling.system.data.get.manager.sales.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServicesContextPathInit {

    @Value("${config.services.context-path.data-get-free-ms}")
    private String dataGetFreeMsContextPath;

    @Value("${config.services.context-path.data-get-ms}")
    private String dataGetMsContextPath;

    @Value("${config.services.context-path.data-get-opt-ms}")
    private String dataGetOptMsContextPath;

    @Bean
    public Map<String, String> servicesContextPath() {
        return Map.of(
                "data-get-free-ms", dataGetFreeMsContextPath,
                "data-get-ms", dataGetMsContextPath,
                "data-get-opt-ms", dataGetOptMsContextPath
        );
    }
}
