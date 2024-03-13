package com.orderizer.data.save.manager.validator.impl;

import com.orderizer.data.save.manager.model.request.OrdersSaveRequest;
import com.orderizer.data.save.manager.validator.api.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrdersSaveRequestValidator implements Validator<OrdersSaveRequest> {

    private final OrderSaveRequestValidator orderSaveRequestValidator;

    @Override
    public Mono<OrdersSaveRequest> validate(OrdersSaveRequest orderSaveRequest) {
        return Flux.fromIterable(orderSaveRequest.getOrders())
                .flatMap(orderSaveRequestValidator::validate)
                .collectList()
                .map(orderRequests -> orderSaveRequest);
    }

}
