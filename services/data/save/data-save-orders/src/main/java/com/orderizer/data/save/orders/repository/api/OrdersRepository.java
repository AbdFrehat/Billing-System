package com.orderizer.data.save.orders.repository.api;

import com.orderizer.data.save.orders.model.entity.Order;
import com.orderizer.data.save.orders.model.request.OrdersSaveRequest;
import reactor.core.publisher.Flux;

public interface OrdersRepository {

    Flux<Order> saveOrders(OrdersSaveRequest ordersSaveRequest);
}
