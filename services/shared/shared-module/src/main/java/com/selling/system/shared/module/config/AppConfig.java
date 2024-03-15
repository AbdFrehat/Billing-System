package com.selling.system.shared.module.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "config")
@Data
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private Auth auth;
    private List<String> languages = new ArrayList<>(Arrays.asList("en", "ar"));
    private Logging logging;
    private final Environment environment;

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

    @PostConstruct
    public void init() {
        if(logging != null && logging.getFileName() == null) {
            logging.setFileName(environment.getProperty("spring.application.name") + ".log");
        }
    }


}
