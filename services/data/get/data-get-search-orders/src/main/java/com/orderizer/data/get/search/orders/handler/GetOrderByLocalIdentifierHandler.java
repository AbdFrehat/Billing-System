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
public class GetOrderByLocalIdentifierHandler implements HandlerFunction<ServerResponse> {

    private final OrdersRepository ordersRepository;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return Mono.defer(() -> {
            Optional<String> localIdentifier = request.queryParam("local-identifier");
            Optional<String> storeLocation = request.queryParam("store-location");
            if (localIdentifier.isPresent() && storeLocation.isPresent()) {
                return ordersRepository.findOrderByLocalIdentifier(localIdentifier.get(), storeLocation.get())
                        .flatMap(order -> ServerResponse.ok().bodyValue(order));
            }
            return Mono.error(QueryParamsMissingException::new);
        });
    }
}
