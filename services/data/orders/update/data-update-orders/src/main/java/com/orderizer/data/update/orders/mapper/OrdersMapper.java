package com.orderizer.data.update.orders.mapper;

import com.selling.system.shared.module.api.Mapper;
import com.orderizer.data.update.orders.model.response.OrderResponse;
import com.orderizer.data.update.orders.model.response.OrdersUpdateResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class OrdersMapper implements Mapper<List<OrderResponse>, OrdersUpdateResponse> {

    @Override
    public Mono<OrdersUpdateResponse> map(List<OrderResponse> orders) {
        return Mono.just(OrdersUpdateResponse.builder()
                .orders(orders)
                .build());
    }
}
