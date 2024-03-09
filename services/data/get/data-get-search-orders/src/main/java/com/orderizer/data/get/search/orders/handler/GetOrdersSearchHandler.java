package com.orderizer.data.get.search.orders.handler;

import com.orderizer.data.get.search.orders.mapper.api.Mapper;
import com.orderizer.data.get.search.orders.model.entity.Order;
import com.orderizer.data.get.search.orders.model.request.OrdersGetRequest;
import com.orderizer.data.get.search.orders.model.response.OrderResponse;
import com.orderizer.data.get.search.orders.model.response.OrdersResponse;
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
public class GetOrdersSearchHandler implements HandlerFunction<ServerResponse> {


    private final OrdersRepository ordersRepository;

    private final Mapper<Order, OrderResponse> mapper;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(OrdersGetRequest.class)
                .map(ordersRepository::findOrders)
                .flatMap(orderFlux -> orderFlux.flatMap(mapper::map).collectList())
                .map(orderResponses -> OrdersResponse.builder().orders(orderResponses).build())
                .flatMap(ordersResponse -> ServerResponse.ok().bodyValue(ordersResponse));

    }
}
