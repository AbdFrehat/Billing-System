package com.orderizer.data.sync.orders.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config")
@Data
public class LocalAppConfig {

    private Services services;

    private Batch batch;

    @Data
    public static class Services {

        private ContextPath contextPath;

        @Data
        public static class ContextPath {
            private String dataStoresManager;
        }
    }

    @Data
    public static class Batch {
        private int size;
        private int delay;
    }
}
