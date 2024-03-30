package com.orderizer.data.update.orders.modification.impl;

import com.orderizer.data.update.orders.model.entity.Order;
import com.orderizer.data.update.orders.model.request.OrderRequest;
import com.orderizer.data.update.orders.modification.api.Modifier;
import com.orderizer.data.update.orders.util.OrderUtil;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class OrderModifier implements Modifier<OrderRequest, Order> {

    @Override
    public Mono<Order> modify(OrderRequest request, Order order) {
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
