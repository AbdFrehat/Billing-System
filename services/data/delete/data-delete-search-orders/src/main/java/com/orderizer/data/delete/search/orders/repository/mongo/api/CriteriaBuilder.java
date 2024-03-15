package com.orderizer.data.delete.search.orders.repository.mongo.api;

import com.orderizer.data.delete.search.orders.model.field.Field;
import org.springframework.data.mongodb.core.query.Criteria;
import reactor.core.publisher.Mono;

public interface CriteriaBuilder {

    Mono<Criteria> build(Field field);
}
