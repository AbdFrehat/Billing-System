package com.orderizer.data.save.orders.mapper.impl;

import com.orderizer.data.save.orders.mapper.api.Mapper;
import com.orderizer.data.save.orders.model.entity.Order;
import com.orderizer.data.save.orders.model.response.OrderResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class OrderMapper implements Mapper<Order, OrderResponse> {
    @Override
    public Mono<OrderResponse> map(Order order) {
        return Mono.just(OrderResponse.builder()
                .purchaseMethod(order.getPurchaseMethod())
                .storeLocation(order.getStoreLocation())
                .localIdentifier(order.getLocalIdentifier())
                .globalIdentifier(order.getGlobalIdentifier())
                .customer(order.getCustomer())
                .orderDate(order.getOrderDate())
                .totalPrice(order.getTotalPrice())
                .items(order.getItems())
                .couponUsed(order.isCouponUsed())
                .build());
    }
}
