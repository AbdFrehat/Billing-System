package com.selling.system.shared.module.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "config")
@Getter
@Setter
@Configuration
public class AppConfig {

    private Auth auth;
    private Services services;
    private List<String> languages = new ArrayList<>(Arrays.asList("en", "ar"));

    @Getter
    @Setter
    public static class Auth {
        private String key;
    }

    @Getter
    @Setter
    public static class Services {
        private ContextPath contextPath;
    }

    @Getter
    @Setter
    public static class ContextPath {
        private Map<String, String> paths;
    }

}
