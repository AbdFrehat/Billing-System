package com.orderizer.data.update.order.mapper;

import com.orderizer.data.update.order.model.entity.Order;
import com.orderizer.data.update.order.model.response.OrderUpdateResponse;
import com.orderizer.core.api.Mapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class OrderMapper implements Mapper<Order, OrderUpdateResponse> {
    @Override
    public Mono<OrderUpdateResponse> map(Order order) {
        return Mono.just(OrderUpdateResponse.builder()
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
