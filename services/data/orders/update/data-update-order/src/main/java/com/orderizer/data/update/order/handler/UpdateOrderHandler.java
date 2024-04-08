package com.orderizer.data.update.order.handler;

import com.orderizer.data.update.order.model.entity.Order;
import com.orderizer.data.update.order.model.request.OrderUpdateRequest;
import com.orderizer.data.update.order.model.response.OrderUpdateResponse;
import com.orderizer.data.update.order.modification.api.Modifier;
import com.orderizer.data.update.order.repository.api.OrdersRepository;
import com.orderizer.core.api.Mapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UpdateOrderHandler implements HandlerFunction<ServerResponse> {

    private final OrdersRepository ordersRepository;

    private final Modifier<OrderUpdateRequest, Order> modifier;

    private final Mapper<Order, OrderUpdateResponse> mapper;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(OrderUpdateRequest.class)
                .flatMap(orderUpdateRequest -> ordersRepository.findOrderByLocalIdentifier(orderUpdateRequest.getLocalIdentifier(), orderUpdateRequest.getStoreLocation())
                        .flatMap(order -> modifier.modify(orderUpdateRequest, order)))
                .flatMap(ordersRepository::updateOrder)
                .flatMap(mapper::map)
                .flatMap(order -> ServerResponse.accepted().bodyValue(order));
    }
}
