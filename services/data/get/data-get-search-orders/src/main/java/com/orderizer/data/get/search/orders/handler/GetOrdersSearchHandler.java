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

import static com.selling.system.shared.module.utils.QueryParamsUtil.getQueryParam;

@Component
@RequiredArgsConstructor
public class GetOrdersSearchHandler implements HandlerFunction<ServerResponse> {


    private final OrdersRepository ordersRepository;

    private final Mapper<Order, OrderResponse> mapper;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return Mono.zip(request.bodyToMono(OrdersGetRequest.class),
                        getQueryParam(request, "page", Integer::parseInt).onErrorReturn(0),
                        getQueryParam(request, "size", Integer::parseInt).onErrorReturn(10))
                .map(tuple -> ordersRepository.findOrders(tuple.getT1(), tuple.getT2(), tuple.getT3()))
                .flatMap(orderFlux -> orderFlux.flatMap(mapper::map).collectList())
                .map(orderResponses -> OrdersResponse.builder().orders(orderResponses).build())
                .flatMap(ordersResponse -> ServerResponse.ok().bodyValue(ordersResponse));
    }
}
