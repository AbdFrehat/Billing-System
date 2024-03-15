package com.orderizer.data.delete.search.orders.handler;

import com.orderizer.data.delete.search.orders.model.request.DeleteOrdersRequest;
import com.orderizer.data.delete.search.orders.repository.api.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DeleteOrdersSearchHandler implements HandlerFunction<ServerResponse> {

    private final OrdersRepository ordersRepository;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(DeleteOrdersRequest.class)
                .flatMap(ordersDeleteRequest -> ordersRepository.deleteOrders(ordersDeleteRequest)
                        .collectList()
                        .then(ServerResponse.noContent().build()));
    }
}
