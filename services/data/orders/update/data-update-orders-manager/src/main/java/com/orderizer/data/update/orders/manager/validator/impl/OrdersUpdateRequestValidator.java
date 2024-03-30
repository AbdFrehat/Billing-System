package com.orderizer.data.update.orders.manager.validator.impl;

import com.orderizer.data.update.orders.manager.model.request.OrdersUpdateRequest;
import com.orderizer.data.update.orders.manager.validator.api.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrdersUpdateRequestValidator implements Validator<OrdersUpdateRequest> {

    private final OrderUpdateRequestValidator orderUpdateRequestValidator;

    @Override
    public Mono<OrdersUpdateRequest> validate(OrdersUpdateRequest orderSaveRequest) {
        return Flux.fromIterable(orderSaveRequest.getOrders())
                .flatMap(orderUpdateRequestValidator::validate)
                .collectList()
                .map(orderRequests -> orderSaveRequest);
    }

}
