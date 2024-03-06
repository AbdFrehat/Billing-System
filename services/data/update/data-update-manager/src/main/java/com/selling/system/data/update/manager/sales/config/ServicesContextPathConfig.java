package com.selling.system.data.update.manager.sales.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "config.services.context-path")
public class ServicesContextPathConfig {

    private String dataUpdateMs;

    private String dataUpdateMultiMs;
}
