package com.orderizer.kafka.orders.consumer.mapper;

import com.orderizer.kafka.orders.consumer.model.request.DeleteOrdersByGlobalIdentifiersRequest;
import com.selling.system.shared.module.api.Mapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class DeleteOrdersMapper implements Mapper<List<Long>, DeleteOrdersByGlobalIdentifiersRequest> {
    @Override
    public Mono<DeleteOrdersByGlobalIdentifiersRequest> map(List<Long> globalIdentifiers) {
        return Mono.defer(() -> Mono.just(DeleteOrdersByGlobalIdentifiersRequest.builder()
                .globalIdentifiers(globalIdentifiers)
                .build()));
    }
}
