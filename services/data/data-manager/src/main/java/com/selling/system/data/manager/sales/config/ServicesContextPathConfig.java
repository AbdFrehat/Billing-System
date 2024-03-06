package com.selling.system.data.manager.sales.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "config.services.context-path")
@Configuration
@Data
public class ServicesContextPathConfig {

    private String DataGetManagerMs;

    private String DataDeleteManagerMs;

    private String DataSaveManagerMs;

    private String DataUpdateManagerMs;

}
