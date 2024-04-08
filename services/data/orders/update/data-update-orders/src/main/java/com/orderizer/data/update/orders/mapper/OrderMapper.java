package com.orderizer.data.update.orders.mapper;

import com.orderizer.data.update.orders.model.entity.Order;
import com.orderizer.data.update.orders.model.response.OrderResponse;
import com.orderizer.core.api.Mapper;
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
