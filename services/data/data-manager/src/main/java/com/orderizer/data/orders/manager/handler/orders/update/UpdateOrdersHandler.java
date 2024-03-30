package com.orderizer.data.orders.manager.handler.orders.update;

import com.orderizer.data.orders.manager.config.LocalAppConfig;
import com.orderizer.data.orders.manager.model.request.OrdersUpdateRequest;
import com.orderizer.data.orders.manager.model.response.OrdersUpdateResponse;
import com.selling.system.shared.module.handlers.ClientExceptionHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UpdateOrdersHandler implements HandlerFunction<ServerResponse> {

    private final WebClient webClient;


    public UpdateOrdersHandler(LocalAppConfig localAppConfig, WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(localAppConfig.getServices().getContextPath().getDataUpdateOrdersManager()).build();
    }

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(OrdersUpdateRequest.class)
                .flatMap(ordersUpdateRequest -> webClient.patch()
                        .bodyValue(ordersUpdateRequest)
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, new ClientExceptionHandler("data-update-orders"))
                        .bodyToMono(OrdersUpdateResponse.class))
                .flatMap(ordersUpdateResponse -> ServerResponse.accepted().bodyValue(ordersUpdateResponse));
    }
}
