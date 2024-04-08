package com.orderizer.data.get.orders.manager.handler.search;

import com.orderizer.data.get.orders.manager.config.LocalAppConfig;
import com.orderizer.data.get.orders.manager.model.response.OrderResponse;
import com.orderizer.core.handlers.ClientExceptionHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static com.orderizer.core.utils.QueryParamsUtil.getQueryParam;

@Component

public class GetOrderByLocalIdentifierHandler implements HandlerFunction<ServerResponse> {

    private final WebClient webClient;

    public GetOrderByLocalIdentifierHandler(WebClient.Builder webClientBuilder, LocalAppConfig localAppConfig) {
        this.webClient = webClientBuilder.baseUrl(localAppConfig.getServices().getContextPath().getDataGetSearchOrders()).build();
    }


    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return Mono.zip(getQueryParam(request, "local-identifier", Long::parseLong),
                        getQueryParam(request, "store-location", Function.identity()))
                .flatMap(tuple -> webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/local")
                                .queryParam("local-identifier", tuple.getT1())
                                .queryParam("store-location", tuple.getT2())
                                .build())
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, new ClientExceptionHandler("data-get-search-orders"))
                        .bodyToMono(OrderResponse.class))
                .flatMap(ServerResponse.ok()::bodyValue);
    }
}
