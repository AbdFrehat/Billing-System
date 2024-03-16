package com.orderizer.data.update.orders.manager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "config.services.context-path")
public class ServicesContextPathConfig {

    private String dataUpdateMs;

    private String dataUpdateMultiMs;
}
