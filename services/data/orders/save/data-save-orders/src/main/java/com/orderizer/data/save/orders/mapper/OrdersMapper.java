package com.orderizer.data.save.orders.mapper;

import com.orderizer.core.api.Mapper;
import com.orderizer.data.save.orders.model.response.OrderResponse;
import com.orderizer.data.save.orders.model.response.OrdersSaveResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class OrdersMapper implements Mapper<List<OrderResponse>, OrdersSaveResponse> {

    @Override
    public Mono<OrdersSaveResponse> map(List<OrderResponse> orders) {
        return Mono.just(OrdersSaveResponse.builder()
                .orders(orders)
                .build());
    }
}
