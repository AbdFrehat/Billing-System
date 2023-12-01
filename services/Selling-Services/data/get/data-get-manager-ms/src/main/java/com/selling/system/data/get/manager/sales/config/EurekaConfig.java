package com.selling.system.data.get.manager.sales.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
@EnableEurekaServer
@ConditionalOnProperty("spring.cloud.discovery.enabled")
public class EurekaConfig {


}
