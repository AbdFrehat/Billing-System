package com.orderizer.data.save.orders.manager.handler;

import com.orderizer.data.save.orders.manager.config.LocalAppConfig;
import com.orderizer.data.save.orders.manager.model.request.OrdersSaveRequest;
import com.orderizer.data.save.orders.manager.model.response.OrdersSaveResponse;
import com.orderizer.data.save.orders.manager.validator.api.Validator;
import com.orderizer.core.handlers.ClientExceptionHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SaveOrdersHandler implements HandlerFunction<ServerResponse> {

    private final WebClient webClient;

    private final Validator<OrdersSaveRequest> validator;

    public SaveOrdersHandler(WebClient.Builder webClientBuilder, LocalAppConfig localAppConfig, Validator<OrdersSaveRequest> validator) {
        this.validator = validator;
        this.webClient = webClientBuilder.baseUrl(localAppConfig.getServices().getContextPath().getDataSaveOrders()).build();
    }

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(OrdersSaveRequest.class)
                .flatMap(validator::validate)
                .flatMap(orderSaveRequest -> webClient.post()
                        .bodyValue(orderSaveRequest)
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, new ClientExceptionHandler("data-save-orders"))
                        .bodyToMono(OrdersSaveResponse.class)
                        .flatMap(ServerResponse.status(HttpStatus.CREATED)::bodyValue));
    }
}
