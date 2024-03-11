package com.orderizer.data.save.order.repository.api;

import com.orderizer.data.save.order.model.entity.Order;
import com.orderizer.data.save.order.model.request.OrderSaveRequest;
import reactor.core.publisher.Mono;

public interface OrdersRepository {

    Mono<Order> saveOrder(OrderSaveRequest orderSaveRequest);

}
