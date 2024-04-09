package com.orderizer.export.data.json.client;

import com.orderizer.core.api.Client;
import com.orderizer.export.data.json.config.LocalAppConfig;
import com.orderizer.export.data.json.model.client.request.OrdersSearchRequest;
import com.orderizer.export.data.json.model.client.response.OrdersResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class DataManagerClient implements Client<OrdersSearchRequest, OrdersResponse> {

    private final WebClient webClient;

    private final LocalAppConfig localAppConfig;

    public DataManagerClient(LocalAppConfig localAppConfig, WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(localAppConfig.getServices().getContextPath().getDataManager()).build();
        this.localAppConfig = localAppConfig;
    }


    @Override
    public Mono<OrdersResponse> call(OrdersSearchRequest ordersSearchRequest, Map<String, Object> queryParams) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/orders/get/search")
                        .queryParam("page", queryParams.getOrDefault("page", 0))
                        .queryParam("size", localAppConfig.getPagination().getSize())
                        .build())
                .bodyValue(ordersSearchRequest)
                .retrieve()
                .bodyToMono(OrdersResponse.class);
    }
}
