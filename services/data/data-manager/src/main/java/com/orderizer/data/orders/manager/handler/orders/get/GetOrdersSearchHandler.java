package com.orderizer.data.orders.manager.handler.orders.get;

import com.orderizer.data.orders.manager.config.LocalAppConfig;
import com.orderizer.data.orders.manager.model.request.OrdersGetRequest;
import com.orderizer.data.orders.manager.model.response.OrdersResponse;
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

public class GetOrdersSearchHandler implements HandlerFunction<ServerResponse> {

    private final WebClient webClient;

    public GetOrdersSearchHandler(WebClient.Builder webClientBuilder, LocalAppConfig localAppConfig) {
        this.webClient = webClientBuilder.baseUrl(localAppConfig.getServices().getContextPath().getDataGetOrdersManager()).build();
    }

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return Mono.zip(request.bodyToMono(OrdersGetRequest.class),
                        getQueryParam(request, "page", Integer::parseInt).onErrorReturn(0),
                        getQueryParam(request, "size", Integer::parseInt).onErrorReturn(10))
                .flatMap(tuple -> webClient.post()
                        .uri(uriBuilder -> uriBuilder.path("/search")
                                .queryParam("page", tuple.getT2())
                                .queryParam("size", tuple.getT3())
                                .build())
                        .bodyValue(tuple.getT1())
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, new ClientExceptionHandler("data-get-orders-manager"))
                        .bodyToMono(OrdersResponse.class)
                        .flatMap(ServerResponse.ok()::bodyValue));
    }
}
