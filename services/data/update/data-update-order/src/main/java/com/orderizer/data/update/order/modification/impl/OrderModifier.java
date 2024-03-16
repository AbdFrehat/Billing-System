package com.orderizer.data.update.order.modification.impl;

import com.orderizer.data.update.order.model.entity.Order;
import com.orderizer.data.update.order.model.request.OrderUpdateRequest;
import com.orderizer.data.update.order.modification.api.Modifier;
import com.orderizer.data.update.order.util.OrderUtil;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class OrderModifier implements Modifier<Order, OrderUpdateRequest> {

    @Override
    public Mono<Order> modify(Order order, OrderUpdateRequest request) {
        return OrderUtil.calculateTotalPrice(request.getItems()).map(totalPrice -> {
            order.setCouponUsed(request.isCouponUsed());
            order.setCustomer(request.getCustomer());
            order.setItems(request.getItems());
            order.setTotalPrice(totalPrice);
            order.setPurchaseMethod(request.getPurchaseMethod());
            return order;
        });
    }
}
