package com.selling.system.auth.manager.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "config")
@Getter
@Setter
public class AppConfig {

    private Auth auth;
    private Services services;

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
