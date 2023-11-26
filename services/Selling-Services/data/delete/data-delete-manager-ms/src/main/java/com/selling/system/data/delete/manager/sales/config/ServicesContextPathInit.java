package com.selling.system.data.delete.manager.sales.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServicesContextPathInit {

    @Value("${config.services.context-path.data-delete-ms}")
    private String dataDeleteMsContextPath;

    @Value("${config.services.context-path.data-delete-multi-ms}")
    private String dataDeleteMultiMsContextPath;

    @Value("${config.services.context-path.data-delete-query-ms}")
    private String dataDeleteQueryMsContextPath;


    @Bean
    public Map<String, String> servicesContextPath() {
        return Map.of(
                "data-delete-ms", dataDeleteMsContextPath,
                "data-delete-multi-ms", dataDeleteMultiMsContextPath,
                "data-delete-query-ms", dataDeleteQueryMsContextPath
        );
    }
}
