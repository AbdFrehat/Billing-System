package com.orderizer.data.delete.orders.manager.handler;

import com.orderizer.data.delete.orders.manager.config.LocalAppConfig;
import com.selling.system.shared.module.handlers.ClientExceptionHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static com.selling.system.shared.module.utils.QueryParamsUtil.getQueryParam;

@Component
public class DeleteOrderByLocalIdentifierHandler implements HandlerFunction<ServerResponse> {

    private final WebClient webClient;

    public DeleteOrderByLocalIdentifierHandler(LocalAppConfig localAppConfig, WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl(localAppConfig.getServices().getContextPath().getDataDeleteOrder()).build();
    }

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return Mono.zip(getQueryParam(request, "local-identifier", Long::parseLong),
                        getQueryParam(request, "store-location", Function.identity()))
                .flatMap(tuple -> webClient.delete()
                        .uri(uriBuilder -> uriBuilder.path("/local")
                                .queryParam("local-identifier", tuple.getT1())
                                .queryParam("store-location", tuple.getT2())
                                .build())
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, new ClientExceptionHandler("data-delete-order"))
                        .toBodilessEntity()
                        .then(ServerResponse.noContent().build()));
    }
}
