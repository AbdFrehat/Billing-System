package com.selling.system.auth.manager.client.impl;

import com.selling.system.auth.manager.client.api.ClientsServiceClient;
import com.selling.system.auth.manager.model.client.response.ClientResponse;
import com.selling.system.shared.module.config.AppConfig;
import com.selling.system.shared.module.handlers.ClientExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClientsServiceClientImpl implements ClientsServiceClient {

    private final WebClient.Builder webClientBuilder;

    private final AppConfig appConfig;

    private static final String serviceName = "auth-clients-manager";

    @Override
    public Mono<ClientResponse> retrieveClientById(String clientId) {
        return webClientBuilder.build()
                .get()
                .uri(getUri(clientId))
                .header("Accept-Language", "ar")
                .retrieve()
                .onStatus(HttpStatusCode::isError, new ClientExceptionHandler(serviceName))
                .bodyToMono(ClientResponse.class);
    }

    private String getUri(String clientId) {
        return UriComponentsBuilder.fromHttpUrl(appConfig.getServices()
                        .getContextPath()
                        .getPaths()
                        .get(serviceName) + clientId)
                .toUriString();
    }
}
