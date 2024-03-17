package com.orderizer.source.pull.orders.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "config")
@Configuration
@Data
public class LocalAppConfig {

    private Emit emit;


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


}
