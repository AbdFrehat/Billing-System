package com.orderizer.source.random.generator.orders.generator.api;


import com.orderizer.source.random.generator.orders.model.emit.Max;
import com.orderizer.source.random.generator.orders.model.request.OrderSaveRequest;
import reactor.core.publisher.Mono;

public interface OrdersGenerator {

    Mono<OrderSaveRequest> generateRandomSaveOrderRequest(Max max);

}
