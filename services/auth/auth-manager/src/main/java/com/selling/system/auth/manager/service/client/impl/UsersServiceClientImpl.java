package com.selling.system.auth.manager.service.client.impl;

import com.selling.system.auth.manager.model.client.response.UserResponse;
import com.selling.system.auth.manager.service.client.api.UsersServiceClient;
import com.selling.system.shared.module.config.AppConfig;
import com.selling.system.shared.module.handlers.ClientExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersServiceClientImpl implements UsersServiceClient {

    private final WebClient.Builder webClientBuilder;
    private final AppConfig appConfig;
    private static final String serviceName = "auth-users-manager";

    @Override
    public Mono<UserResponse> retrieveUserByUsername(String username) {
        return webClientBuilder.build()
                .get()
                .uri(getUri(username))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::isError, new ClientExceptionHandler(serviceName))
                .bodyToMono(UserResponse.class);
    }

    private String getUri(String username) {
        return UriComponentsBuilder.fromHttpUrl(appConfig.getServices()
                        .getContextPath()
                        .getPaths()
                        .get(serviceName) + username)
                .toUriString();
    }
}
