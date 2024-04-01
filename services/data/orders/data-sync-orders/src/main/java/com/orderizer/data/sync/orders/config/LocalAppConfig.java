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
    private Delay delay;
    private Queue queue;
    private Elasticsearch elasticsearch;
    private Threads threads;

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
    }

    @Data
    public static class Delay {
        private int storesReader;
        private int mongoReader;
        private int elasticWriter;
    }

    @Data
    public static class Queue {
        private int size;
    }

    @Data
    public static class Elasticsearch {
        private String caFingerprint;
        private String username;
        private String password;
        private String uri;
        private boolean securityEnabled;
    }

    @Data
    public static class Threads {

        private Elasticsearch elasticsearch;

        @Data
        public static class Elasticsearch {
            private int size;
        }
    }
}
