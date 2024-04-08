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

import static com.orderizer.core.utils.QueryParamsUtil.getQueryParam;

@Component

public class GetOrderByGlobalIdentifierHandler implements HandlerFunction<ServerResponse> {

    private final WebClient webClient;

    public GetOrderByGlobalIdentifierHandler(WebClient.Builder webClientBuilder, LocalAppConfig localAppConfig) {
        this.webClient = webClientBuilder.baseUrl(localAppConfig.getServices().getContextPath().getDataGetSearchOrders()).build();
    }

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return getQueryParam(request, "global-identifier", Long::parseLong)
                .flatMap(globalIdentifier -> webClient
                        .get()
                        .uri(uriBuilder -> uriBuilder.path("/global").queryParam("global-identifier", globalIdentifier).build())
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, new ClientExceptionHandler("data-get-search-orders"))
                        .bodyToMono(OrderResponse.class))
                .flatMap(ServerResponse.ok()::bodyValue);
    }
}
