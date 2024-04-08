package com.orderizer.data.save.order.mapper;

import com.orderizer.data.save.order.model.entity.Order;
import com.orderizer.data.save.order.model.response.OrderSaveResponse;
import com.orderizer.core.api.Mapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class OrdersMapper implements Mapper<Order, OrderSaveResponse> {
    @Override
    public Mono<OrderSaveResponse> map(Order order) {
        return Mono.just(OrderSaveResponse.builder()
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
