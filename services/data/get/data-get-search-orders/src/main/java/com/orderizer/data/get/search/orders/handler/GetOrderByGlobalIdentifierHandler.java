package com.orderizer.data.get.search.orders.handler;

import com.orderizer.data.get.search.orders.exception.QueryParamsMissingException;
import com.orderizer.data.get.search.orders.repository.api.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetOrderByGlobalIdentifierHandler implements HandlerFunction<ServerResponse> {

    private final OrdersRepository ordersRepository;
    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return Mono.defer(() -> {
            Optional<String> globalIdentifier = request.queryParam("global-identifier");
            return globalIdentifier.map(s -> ordersRepository.findOrderByGlobalIdentifier(s)
                    .flatMap(order -> ServerResponse.ok().bodyValue(order))).orElseGet(() -> Mono.error(QueryParamsMissingException::new));
        });
    }
}
