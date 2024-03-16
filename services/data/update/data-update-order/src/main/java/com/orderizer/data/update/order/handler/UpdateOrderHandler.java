package com.orderizer.data.update.order.handler;

import com.orderizer.data.update.order.model.entity.Order;
import com.orderizer.data.update.order.model.request.OrderUpdateRequest;
import com.orderizer.data.update.order.modification.api.Modifier;
import com.orderizer.data.update.order.repository.api.OrdersRepository;
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
public class UpdateOrderHandler implements HandlerFunction<ServerResponse> {

    private final OrdersRepository ordersRepository;

    private final Modifier<Order, OrderUpdateRequest> modifier;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return Mono.zip(request.bodyToMono(OrderUpdateRequest.class),
                        getQueryParam(request, "global-identifier", Long::parseLong))
                .flatMap(tuple -> ordersRepository.findOrderByGlobalIdentifier(tuple.getT2())
                        .flatMap(order -> modifier.modify(order, tuple.getT1())))
                .flatMap(ordersRepository::updateOrder)
                .flatMap(order -> ServerResponse.accepted().bodyValue(order));
    }
}
