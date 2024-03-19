package com.orderizer.data.update.orders.manager.handler;

import com.orderizer.data.update.orders.manager.config.LocalAppConfig;
import com.orderizer.data.update.orders.manager.model.request.OrdersUpdateRequest;
import com.orderizer.data.update.orders.manager.model.response.OrdersUpdateResponse;
import com.orderizer.data.update.orders.manager.validator.api.Validator;
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

    private final Validator<OrdersUpdateRequest> validator;

    public UpdateOrdersHandler(LocalAppConfig localAppConfig, WebClient.Builder webClientBuilder, Validator<OrdersUpdateRequest> validator) {
        this.validator = validator;
        this.webClient = webClientBuilder.baseUrl(localAppConfig.getServices().getContextPath().getDataUpdateOrders()).build();
    }

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(OrdersUpdateRequest.class)
                .flatMap(validator::validate)
                .flatMap(ordersUpdateRequest -> webClient.patch()
                        .bodyValue(ordersUpdateRequest)
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, new ClientExceptionHandler("data-update-orders"))
                        .bodyToMono(OrdersUpdateResponse.class))
                .flatMap(ordersUpdateResponse -> ServerResponse.accepted().bodyValue(ordersUpdateResponse));
    }
}
