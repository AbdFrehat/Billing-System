package com.selling.system.auth.manager.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties(prefix = "config")
@Configuration
@Getter
@Setter
public class LocalAppConfig {

    private Auth auth;

    private Services services;

    @Data
    public static class Services {

        private ContextPath contextPath;

        @Data
        public static class ContextPath {

            private String authClientsManager;

            private String authUsersManager;

        }
    }

    @Getter
    @Setter
    public static class Auth {
        private String key;
        private AccessToken accessToken;
        private RefreshToken refreshToken;
        private AuthCode authCode;

        @Getter
        @Setter
        public static class AccessToken {
            private Long expirationTime;
        }

        @Getter
        @Setter
        public static class RefreshToken {
            private Long expirationTime;
        }

        @Getter
        @Setter
        public static class AuthCode {
            private int length;
            private Long expirationTime;
        }
    }


}
