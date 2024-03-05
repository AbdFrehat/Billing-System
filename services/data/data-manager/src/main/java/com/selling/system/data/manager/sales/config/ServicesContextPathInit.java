package com.selling.system.data.manager.sales.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "config.services.context-path")
@Configuration
@Slf4j
@Data
public class ServicesContextPathInit {

    private String DataGetManagerMs;

    private String DataDeleteManagerMs;

    private String DataSaveManagerMs;

    private String DataUpdateManagerMs;

}
