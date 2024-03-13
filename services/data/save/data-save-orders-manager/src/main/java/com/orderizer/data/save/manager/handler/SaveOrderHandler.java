package com.orderizer.data.save.manager.handler;

import com.orderizer.data.save.manager.config.LocalAppConfig;
import com.orderizer.data.save.manager.model.request.OrderSaveRequest;
import com.orderizer.data.save.manager.model.response.OrderSaveResponse;
import com.orderizer.data.save.manager.validator.api.Validator;
import com.selling.system.shared.module.handlers.ClientExceptionHandler;
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
public class SaveOrderHandler implements HandlerFunction<ServerResponse> {

    private final WebClient webClient;

    private final Validator<OrderSaveRequest> validator;

    public SaveOrderHandler(WebClient.Builder webClientBuilder, LocalAppConfig localAppConfig, Validator<OrderSaveRequest> validator) {
        this.validator = validator;
        this.webClient = webClientBuilder.baseUrl(localAppConfig.getServices().getContextPath().getDataSaveOrder()).build();
    }

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(OrderSaveRequest.class)
                .flatMap(validator::validate)
                .flatMap(orderSaveRequest -> webClient.post()
                        .bodyValue(orderSaveRequest)
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, new ClientExceptionHandler("data-save-order"))
                        .bodyToMono(OrderSaveResponse.class)
                        .flatMap(ServerResponse.status(HttpStatus.CREATED)::bodyValue));
    }
}
