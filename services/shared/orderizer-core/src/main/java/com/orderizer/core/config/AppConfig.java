package com.orderizer.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "config")
@Data
@Configuration
public class AppConfig {

    private Auth auth;
    private List<String> languages = new ArrayList<>(Arrays.asList("en", "ar"));
    private Logging logging;

    @Data
    public static class Auth {
        private String key;
    }

    @Data
    public static class Logging {
        private String fileName;

        private String logFilePath = "./logs/";

        private String logPattern = "%d [%thread] %-5level %logger{35} - %msg%n";

        private String archivePattern = ".%d{yyyy-MM-dd}.%i.gz";

        private String totalSize = "10MB";

        private String maxSize = "1MB";

        private int maxHistory = 10;

        private boolean enable = false;

    }

}
