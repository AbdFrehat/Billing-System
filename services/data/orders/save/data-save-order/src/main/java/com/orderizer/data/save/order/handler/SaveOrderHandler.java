package com.orderizer.data.save.order.handler;

import com.orderizer.data.save.order.model.entity.Order;
import com.orderizer.data.save.order.model.request.OrderSaveRequest;
import com.orderizer.data.save.order.model.response.OrderSaveResponse;
import com.orderizer.data.save.order.repository.api.OrdersRepository;
import com.selling.system.shared.module.api.Mapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SaveOrderHandler implements HandlerFunction<ServerResponse> {

    private final OrdersRepository ordersRepository;

    private final Mapper<Order, OrderSaveResponse> mapper;


    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(OrderSaveRequest.class)
                .flatMap(ordersRepository::saveOrder)
                .flatMap(mapper::map)
                .flatMap(ServerResponse.status(HttpStatus.CREATED)::bodyValue);
    }
}
