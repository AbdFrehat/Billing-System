package com.orderizer.data.delete.search.orders.repository.mongo.impl;

import com.orderizer.data.delete.search.orders.model.field.Field;
import com.orderizer.data.delete.search.orders.model.request.DeleteOrdersRequest;
import com.orderizer.data.delete.search.orders.repository.mongo.api.CriteriaBuilder;
import com.orderizer.data.delete.search.orders.repository.mongo.api.QueryBuilder;
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
    public Mono<Query> build(DeleteOrdersRequest deleteOrdersRequest) {
        return Mono.defer(() -> {
            Query query = new Query();
            return Mono.when(
                    processFields(deleteOrdersRequest.getExactFields(), query),
                    processFields(deleteOrdersRequest.getMatchFields(), query),
                    processFields(deleteOrdersRequest.getRangeFields(), query),
                    processFields(deleteOrdersRequest.getRangeDateFields(), query),
                    processFields(deleteOrdersRequest.getListExactFields(), query),
                    processFields(deleteOrdersRequest.getListMatchFields(), query),
                    processFields(deleteOrdersRequest.getListRangeFields(), query),
                    processFields(deleteOrdersRequest.getListRangeDateFields(), query)
            ).thenReturn(query);
        });
    }

    @NotNull
    private Mono<Void> processFields(List<? extends Field> fields, Query query) {
        return Mono.defer(() -> fields != null ? Flux.fromIterable(fields).flatMap(criteriaBuilder::build).map(query::addCriteria).then() : Mono.empty());
    }
}
