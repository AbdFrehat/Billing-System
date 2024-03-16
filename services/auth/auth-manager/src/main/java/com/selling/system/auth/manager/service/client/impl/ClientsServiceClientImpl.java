package com.selling.system.auth.manager.service.client.impl;

import com.selling.system.auth.manager.config.LocalAppConfig;
import com.selling.system.auth.manager.model.client.response.ClientResponse;
import com.selling.system.auth.manager.service.client.api.ClientsServiceClient;
import com.selling.system.shared.module.handlers.ClientExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientsServiceClientImpl implements ClientsServiceClient {

    private final WebClient.Builder webClientBuilder;
    private final LocalAppConfig localAppConfig;
    private static final String serviceName = "auth-clients-manager";

    @Override
    public Mono<ClientResponse> retrieveClientById(String clientId) {
        return webClientBuilder.build()
                .get()
                .uri(getUri(clientId))
                .header("Accept-Language", "en")
                .retrieve()
                .onStatus(HttpStatusCode::isError, new ClientExceptionHandler(serviceName))
                .bodyToMono(ClientResponse.class);
    }

    private String getUri(String clientId) {
        return UriComponentsBuilder.fromHttpUrl(localAppConfig.getServices()
                        .getContextPath()
                        .getAuthClientsManager() + clientId)
                .toUriString();
    }
}
