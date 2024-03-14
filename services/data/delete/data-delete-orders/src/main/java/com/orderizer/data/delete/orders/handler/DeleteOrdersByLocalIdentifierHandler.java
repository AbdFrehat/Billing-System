package com.orderizer.data.delete.orders.handler;

import com.orderizer.data.delete.orders.model.request.DeleteOrdersByLocalIdentifiersRequest;
import com.orderizer.data.delete.orders.repository.api.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DeleteOrdersByLocalIdentifierHandler implements HandlerFunction<ServerResponse> {

    private final OrdersRepository ordersRepository;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(DeleteOrdersByLocalIdentifiersRequest.class)
                .map(ordersRepository::deleteOrdersByLocalIdentifier)
                .flatMap(Flux::collectList)
                .flatMap(deleteResults -> ServerResponse.noContent().build());
    }


}
