package com.selling.system.data.get.manager.sales.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration

@ConditionalOnProperty("spring.cloud.discovery.enabled")
public class EurekaConfig {


}
