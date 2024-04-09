package com.orderizer.export.data.json.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config")
@Data
public class LocalAppConfig {

    private Services services;
    private Pagination pagination;

    @Data
    public static class Services {

        private ContextPath contextPath;

        @Data
        public static class ContextPath {
            private String dataManager;
        }

    }

    @Data
    public static class Pagination {
        private int size;
    }
}
