package com.orderizer.data.get.search.orders.handler;

import com.orderizer.data.get.search.orders.mapper.api.Mapper;
import com.orderizer.data.get.search.orders.model.entity.Order;
import com.orderizer.data.get.search.orders.model.response.OrderResponse;
import com.orderizer.data.get.search.orders.repository.api.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static com.orderizer.core.utils.QueryParamsUtil.getQueryParam;

@Component
@RequiredArgsConstructor
public class GetOrderByLocalIdentifierHandler implements HandlerFunction<ServerResponse> {

    private final OrdersRepository ordersRepository;

    private final Mapper<Order, OrderResponse> mapper;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return Mono.zip(getQueryParam(request, "local-identifier", Long::parseLong),
                        getQueryParam(request, "store-location", Function.identity()))
                .flatMap(tuple -> ordersRepository.findOrderByLocalIdentifier(tuple.getT1(), tuple.getT2())
                        .flatMap(mapper::map)
                        .flatMap(orderResponse -> ServerResponse.ok().bodyValue(orderResponse)));
    }
}
