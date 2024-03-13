package com.orderizer.data.save.orders.handler;

import com.orderizer.data.save.orders.mapper.api.Mapper;
import com.orderizer.data.save.orders.model.entity.Order;
import com.orderizer.data.save.orders.model.request.OrdersSaveRequest;
import com.orderizer.data.save.orders.model.response.OrderResponse;
import com.orderizer.data.save.orders.model.response.OrdersSaveResponse;
import com.orderizer.data.save.orders.repository.api.OrdersRepository;
import com.orderizer.data.save.orders.validator.api.Validator;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SaveOrdersHandler implements HandlerFunction<ServerResponse> {

    private final OrdersRepository ordersRepository;

    private final Mapper<Order, OrderResponse> orderMapper;
    private final Mapper<List<OrderResponse>, OrdersSaveResponse> ordersMapper;
    private final Validator<OrdersSaveRequest> validator;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(OrdersSaveRequest.class)
                .flatMap(validator::validate)
                .map(ordersRepository::saveOrders)
                .flatMap(orderFlux -> orderFlux
                        .flatMap(orderMapper::map).collectList()
                        .flatMap(ordersMapper::map)
                        .flatMap(ServerResponse.status(HttpStatus.CREATED)::bodyValue));
    }

}
