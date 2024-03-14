package com.orderizer.data.delete.orders.manager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "config.services.context-path")
public class ServicesContextPathConfig {

    private String dataDeleteMs;

    private String dataDeleteMultiMs;

    private String dataDeleteQueryMs;

}
