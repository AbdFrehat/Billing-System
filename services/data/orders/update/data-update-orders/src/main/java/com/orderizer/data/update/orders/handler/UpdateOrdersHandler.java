package com.orderizer.data.update.orders.handler;

import com.selling.system.shared.module.api.Mapper;
import com.orderizer.data.update.orders.model.entity.Order;
import com.orderizer.data.update.orders.model.request.OrderRequest;
import com.orderizer.data.update.orders.model.request.OrdersUpdateRequest;
import com.orderizer.data.update.orders.model.response.OrderResponse;
import com.orderizer.data.update.orders.model.response.OrdersUpdateResponse;
import com.orderizer.data.update.orders.modification.api.Modifier;
import com.orderizer.data.update.orders.repository.api.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateOrdersHandler implements HandlerFunction<ServerResponse> {

    private final OrdersRepository ordersRepository;
    private final Modifier<OrderRequest, Order> modifier;
    private final Mapper<Order, OrderResponse> orderResponseMapper;
    private final Mapper<List<OrderResponse>, OrdersUpdateResponse> ordersUpdateResponseMapper;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(OrdersUpdateRequest.class).map(ordersUpdateRequest -> Flux.fromIterable(ordersUpdateRequest.getOrders()))
                .flatMap(orderRequestFlux ->
                        orderRequestFlux.flatMap(orderRequest ->
                                        ordersRepository.findOrderByLocalIdentifier(orderRequest.getLocalIdentifier(), orderRequest.getStoreLocation())
                                                .flatMap(order -> modifier.modify(orderRequest, order))
                                                .flatMap(ordersRepository::updateOrder))
                                .flatMap(orderResponseMapper::map)
                                .collectList()
                                .flatMap(ordersUpdateResponseMapper::map))
                .flatMap(ordersUpdateResponse -> ServerResponse.accepted().bodyValue(ordersUpdateResponse));
    }
}
