package com.orderizer.data.delete.orders.manager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "config")
public class LocalAppConfig {

    private Services services;

    @Data
    public static class Services {

        private ContextPath contextPath;

        @Data
        public static class ContextPath {

            private String dataDeleteOrder;

            private String dataDeleteOrders;

            private String dataDeleteSearchOrders;

        }
    }
    
}
