package com.orderizer.source.push.orders.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "config")
@Configuration
@Data
public class LocalAppConfig {

    private Emit emit;
    private Services services;


    @Data
    public static class Emit {
        private Long duration;
        private Max max;

        @Data
        public static class Max {
            private int items;
            private int tags;
        }
    }

    @Data
    public static class Services {
        private ContextPath contextPath;

        @Data
        public static class ContextPath {
            private String destination;
        }
    }


}
