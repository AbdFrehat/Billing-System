package com.orderizer.data.orders.manager.handler.orders.delete;

import com.orderizer.data.orders.manager.config.LocalAppConfig;
import com.orderizer.data.orders.manager.model.request.DeleteOrdersByGlobalIdentifiersRequest;
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
public class DeleteOrdersByGlobalIdentifierHandler implements HandlerFunction<ServerResponse> {

    private final WebClient webClient;


    public DeleteOrdersByGlobalIdentifierHandler(LocalAppConfig localAppConfig, WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl(localAppConfig.getServices().getContextPath().getDataDeleteOrdersManager()).build();
    }

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(DeleteOrdersByGlobalIdentifiersRequest.class)
                .flatMap(deleteOrdersByGlobalIdentifiersRequest -> webClient.post()
                        .uri(uriBuilder -> uriBuilder.path("/batch/global").build())
                        .bodyValue(deleteOrdersByGlobalIdentifiersRequest)
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, new ClientExceptionHandler("data-delete-orders-manager"))
                        .toBodilessEntity()
                        .then(ServerResponse.noContent().build()));
    }
}
