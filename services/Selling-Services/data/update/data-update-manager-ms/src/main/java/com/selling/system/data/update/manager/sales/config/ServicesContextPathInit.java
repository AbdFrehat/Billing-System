package com.selling.system.data.update.manager.sales.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServicesContextPathInit {

    @Value("${config.services.context-path.data-update-ms}")
    private String dataSaveMsContextPath;

    @Value("${config.services.context-path.data-update-multi-ms}")
    private String dataSaveMultiMsContextPath;


    @Bean
    public Map<String, String> servicesContextPath() {
        return Map.of(
                "data-update-ms", dataSaveMsContextPath,
                "data-update-multi-ms", dataSaveMultiMsContextPath
        );
    }
}
