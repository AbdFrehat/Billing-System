package com.orderizer.data.save.manager.config;

import com.orderizer.data.save.manager.model.config.ContextPath;
import com.orderizer.data.save.manager.model.config.Services;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties(prefix = "config")
@Configuration
@Data
public class LocalAppConfig {

    private Services services;

}