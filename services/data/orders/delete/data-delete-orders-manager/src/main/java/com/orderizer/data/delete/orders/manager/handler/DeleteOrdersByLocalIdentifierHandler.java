package com.orderizer.data.delete.orders.manager.handler;

import com.orderizer.data.delete.orders.manager.config.LocalAppConfig;
import com.orderizer.data.delete.orders.manager.model.request.DeleteOrdersByLocalIdentifiersRequest;
import com.orderizer.data.delete.orders.manager.validator.api.Validator;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class DeleteOrdersByLocalIdentifierHandler implements HandlerFunction<ServerResponse> {

    private final WebClient webClient;

    private final Validator<DeleteOrdersByLocalIdentifiersRequest> validator;

    public DeleteOrdersByLocalIdentifierHandler(LocalAppConfig localAppConfig, WebClient.Builder webClient, Validator<DeleteOrdersByLocalIdentifiersRequest> validator) {
        this.validator = validator;
        this.webClient = webClient.baseUrl(localAppConfig.getServices().getContextPath().getDataDeleteOrders()).build();
    }

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(DeleteOrdersByLocalIdentifiersRequest.class)
                .flatMap(validator::validate)
                .flatMap(deleteOrdersByLocalIdentifiersRequest -> webClient.post()
                        .uri(uriBuilder -> uriBuilder.path("/local").build())
                        .bodyValue(deleteOrdersByLocalIdentifiersRequest)
                        .retrieve()
                        .toBodilessEntity()
                        .then(ServerResponse.noContent().build()));
    }
}
