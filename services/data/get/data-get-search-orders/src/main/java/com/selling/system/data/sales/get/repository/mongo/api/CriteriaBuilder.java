package com.selling.system.data.sales.get.repository.mongo.api;

import com.selling.system.data.sales.get.model.field.Field;
import org.springframework.data.mongodb.core.query.Criteria;
import reactor.core.publisher.Mono;

public interface CriteriaBuilder {

    Mono<Criteria> build(Field field);
}
