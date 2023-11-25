package com.selling.system.data.manager.sales.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServicesContextPathInit {

    @Value("${config.services.context-path.data-get-manager-ms}")
    private String dataGetManagerMsContextPath;

    @Value("${config.services.context-path.data-update-manager-ms}")
    private String dataUpdateManagerMsContextPath;

    @Value("${config.services.context-path.data-save-manager-ms}")
    private String dataSaveManagerMsContextPath;

    @Value("${config.services.context-path.data-delete-manager-ms}")
    private String dataDeleteManagerMsContextPath;

    @Bean
    public Map<String, String> servicesContextPath() {
        return Map.of(
                "data-get-manager-ms", dataGetManagerMsContextPath,
                "data-save-manager-ms", dataSaveManagerMsContextPath,
                "data-update-manager-ms", dataUpdateManagerMsContextPath,
                "data-delete-manager-ms", dataDeleteManagerMsContextPath
        );
    }
}
