package com.orderizer.data.orders.manager.handler.orders.update;

import com.orderizer.data.orders.manager.config.LocalAppConfig;
import com.orderizer.data.orders.manager.model.request.OrderUpdateRequest;
import com.orderizer.data.orders.manager.model.response.OrderUpdateResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatusCode;
import com.orderizer.core.handlers.ClientExceptionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UpdateOrderHandler implements HandlerFunction<ServerResponse> {
    private final WebClient webClient;


    public UpdateOrderHandler(LocalAppConfig localAppConfig, WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(localAppConfig.getServices().getContextPath().getDataUpdateOrdersManager()).build();
    }


    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(OrderUpdateRequest.class)
                .flatMap(orderUpdateRequest -> webClient.patch()
                        .bodyValue(orderUpdateRequest)
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, new ClientExceptionHandler("data-update-orders-manager"))
                        .bodyToMono(OrderUpdateResponse.class))
                .flatMap(orderUpdateResponse -> ServerResponse.accepted().bodyValue(orderUpdateResponse));
    }

}