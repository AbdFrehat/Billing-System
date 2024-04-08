package com.orderizer.data.update.orders.manager.handler;

import com.orderizer.data.update.orders.manager.config.LocalAppConfig;
import com.orderizer.data.update.orders.manager.model.request.OrderUpdateRequest;
import com.orderizer.data.update.orders.manager.model.response.OrderUpdateResponse;
import com.orderizer.data.update.orders.manager.validator.api.Validator;
import com.orderizer.core.handlers.ClientExceptionHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UpdateOrderHandler implements HandlerFunction<ServerResponse> {

    private final WebClient webClient;

    private final Validator<OrderUpdateRequest> validator;

    public UpdateOrderHandler(LocalAppConfig localAppConfig, WebClient.Builder webClientBuilder, Validator<OrderUpdateRequest> validator) {
        this.validator = validator;
        this.webClient = webClientBuilder.baseUrl(localAppConfig.getServices().getContextPath().getDataUpdateOrder()).build();
    }


    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(OrderUpdateRequest.class)
                .flatMap(validator::validate)
                .flatMap(orderUpdateRequest -> webClient.patch()
                        .bodyValue(orderUpdateRequest)
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, new ClientExceptionHandler("data-update-order"))
                        .bodyToMono(OrderUpdateResponse.class))
                .flatMap(orderUpdateResponse -> ServerResponse.accepted().bodyValue(orderUpdateResponse));
    }
}
