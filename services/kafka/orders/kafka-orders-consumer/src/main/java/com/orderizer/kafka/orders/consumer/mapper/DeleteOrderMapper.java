package com.orderizer.kafka.orders.consumer.mapper;

import com.orderizer.kafka.orders.consumer.model.entity.DataDeleteOrderEntity;
import com.selling.system.shared.module.api.Mapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DeleteOrderMapper implements Mapper<DataDeleteOrderEntity, Long> {
    @Override
    public Mono<Long> map(DataDeleteOrderEntity dataDeleteOrderEntity) {
        return Mono.defer(() ->
                Mono.just(dataDeleteOrderEntity.getGlobalIdentifier())
        );
    }
}
