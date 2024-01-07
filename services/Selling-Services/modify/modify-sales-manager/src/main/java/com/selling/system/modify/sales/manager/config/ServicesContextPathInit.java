package com.selling.system.modify.sales.manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServicesContextPathInit {

    @Value("${config.services.context-path.modify-delete-sales-ms}")
    private String modifyDeleteSalesContextPath;

    @Value("${config.services.context-path.modify-update-sales-ms}")
    private String modifyUpdateSalesContextPath;

    @Value("${config.services.context-path.modify-save-sales-ms}")
    private String modifySaveSalesContextPath;


    @Bean
    public Map<String, String> servicesContextPath() {
        return Map.of(
                "modify-delete-sales-ms", modifyDeleteSalesContextPath,
                "modify-update-sales-ms", modifyUpdateSalesContextPath,
                "modify-save-sales-ms", modifySaveSalesContextPath
        );
    }
}
