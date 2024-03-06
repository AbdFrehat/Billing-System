package com.selling.system.data.save.manager.sales.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config.services.context-path")
@Data
public class ServicesContextPathConfig {

    private String dataSaveMs;

    private String dataSaveMultiMs;

}
