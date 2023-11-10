package com.sales.query.shared.models.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.sales.query.shared.models.repository",
        "com.sales.query.shared.models.config",
})
public class SpringConfig {
}
