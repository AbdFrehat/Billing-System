package com.orderizer.data.sync.orders.service.impl;

import com.orderizer.data.sync.orders.config.LocalAppConfig;
import com.orderizer.data.sync.orders.model.client.response.GetStoresResponse;
import com.orderizer.data.sync.orders.service.api.StoresService;
import com.orderizer.core.handlers.ClientExceptionHandler;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StoresServiceImpl implements StoresService {

    private final WebClient webClient;


    public StoresServiceImpl(WebClient.Builder webClientBuilder, LocalAppConfig localAppConfig) {
        this.webClient = webClientBuilder.baseUrl(localAppConfig.getServices().getContextPath().getDataStoresManager()).build();
    }

    @Override
    public Mono<GetStoresResponse> fetchStores() {
        return webClient.get()
                .retrieve()
                .onStatus(HttpStatusCode::isError, new ClientExceptionHandler("data-stores-manager"))
                .bodyToMono(GetStoresResponse.class);
    }
}
