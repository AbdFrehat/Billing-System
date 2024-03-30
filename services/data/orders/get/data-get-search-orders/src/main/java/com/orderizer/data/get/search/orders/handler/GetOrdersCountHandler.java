package com.orderizer.data.get.search.orders.handler;

import com.orderizer.data.get.search.orders.model.request.OrdersGetRequest;
import com.orderizer.data.get.search.orders.model.response.OrdersCountResponse;
import com.orderizer.data.get.search.orders.repository.api.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetOrdersCountHandler implements HandlerFunction<ServerResponse> {

    private final OrdersRepository ordersRepository;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(OrdersGetRequest.class)
                .flatMap(ordersRepository::countOrders)
                .map(count -> OrdersCountResponse.builder().count(count).build())
                .flatMap(ordersCountResponse -> ServerResponse.ok().bodyValue(ordersCountResponse));
    }
}
