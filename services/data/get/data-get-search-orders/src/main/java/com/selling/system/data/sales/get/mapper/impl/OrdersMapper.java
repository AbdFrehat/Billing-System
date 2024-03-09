package com.selling.system.data.sales.get.mapper.impl;

import com.selling.system.data.sales.get.mapper.api.Mapper;
import com.selling.system.data.sales.get.model.entity.Order;
import com.selling.system.data.sales.get.model.response.OrderResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class OrdersMapper implements Mapper<Order, OrderResponse> {
    @Override
    public Mono<OrderResponse> map(Order order) {
        return Mono.just(OrderResponse.builder()
                .purchaseMethod(order.getPurchaseMethod())
                .storeLocation(order.getStoreLocation())
                .localIdentifier(order.getLocalIdentifier())
                .globalIdentifier(order.getGlobalIdentifier())
                .customer(order.getCustomer())
                .orderDate(order.getOrderDate())
                .items(order.getItems())
                .couponUsed(order.isCouponUsed())
                .build());
    }
}
