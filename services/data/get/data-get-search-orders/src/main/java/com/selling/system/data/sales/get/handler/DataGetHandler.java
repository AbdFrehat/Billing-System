package com.selling.system.data.sales.get.handler;

import com.selling.system.data.sales.get.mapper.api.Mapper;
import com.selling.system.data.sales.get.model.entity.Order;
import com.selling.system.data.sales.get.model.request.OrdersGetRequest;
import com.selling.system.data.sales.get.model.response.OrderResponse;
import com.selling.system.data.sales.get.model.response.OrdersResponse;
import com.selling.system.data.sales.get.repository.api.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DataGetHandler implements HandlerFunction<ServerResponse> {


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
