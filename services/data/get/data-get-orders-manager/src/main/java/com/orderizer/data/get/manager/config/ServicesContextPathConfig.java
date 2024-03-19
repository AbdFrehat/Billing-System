package com.orderizer.data.get.manager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "config.services.context-path")
@Configuration
@Data
public class ServicesContextPathConfig {

    private String dataGetFreeMs;

    private String dataGetMs;

    private String dataGetOptMs;


}
