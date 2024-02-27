package com.selling.system.data.save.manager.sales.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServicesContextPathInit {

    @Value("${config.services.context-path.data-save-ms}")
    private String dataSaveMsContextPath;

    @Value("${config.services.context-path.data-save-multi-ms}")
    private String dataSaveMultiMsContextPath;


    @Bean
    public Map<String, String> servicesContextPath() {
        return Map.of(
                "data-save-ms", dataSaveMsContextPath,
                "data-save-multi-ms", dataSaveMultiMsContextPath
        );
    }
}
