package com.selling.system.data.sales.get.repository.mongo.impl;

import com.selling.system.data.sales.get.model.field.Field;
import com.selling.system.data.sales.get.model.request.OrdersGetRequest;
import com.selling.system.data.sales.get.repository.mongo.api.CriteriaBuilder;
import com.selling.system.data.sales.get.repository.mongo.api.QueryBuilder;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Component
@RequiredArgsConstructor
public class QueryBuilderImpl implements QueryBuilder {

    private final CriteriaBuilder criteriaBuilder;

    @Override
    public Mono<Query> build(OrdersGetRequest ordersGetRequest) {
        return Mono.defer(() -> {
            Query query = new Query();
            return Mono.when(
                            processFields(ordersGetRequest.getExactFields(), query),
                            processFields(ordersGetRequest.getMatchFields(), query),
                            processFields(ordersGetRequest.getRangeFields(), query)
                    ).thenReturn(query);
        });
    }

    @NotNull
    private Mono<Void> processFields(List<? extends Field> fields, Query query) {
        return Mono.defer(() -> fields != null ? Flux.fromIterable(fields).flatMap(criteriaBuilder::build).map(query::addCriteria).then() : Mono.empty());
    }
}
