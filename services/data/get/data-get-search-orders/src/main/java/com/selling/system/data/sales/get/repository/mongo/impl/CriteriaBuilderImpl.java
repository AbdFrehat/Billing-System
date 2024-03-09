package com.selling.system.data.sales.get.repository.mongo.impl;

import com.selling.system.data.sales.get.exception.FieldTypeNotSupportedException;
import com.selling.system.data.sales.get.model.field.*;
import com.selling.system.data.sales.get.repository.mongo.api.CriteriaBuilder;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CriteriaBuilderImpl implements CriteriaBuilder {
    @Override
    public Mono<Criteria> build(Field field) {
        if (field instanceof ExactField exactField) {
            return Mono.just(Criteria.where(exactField.getField()).is(exactField.getValue()));
        }
        if (field instanceof MatchField matchField) {
            return Mono.just(Criteria.where(matchField.getField()).regex(matchField.getValue()));
        }
        if (field instanceof RangeField rangeField) {
            return Mono.just(Criteria.where(rangeField.getField()).lte(rangeField.getMax()).gte(rangeField.getMin()));
        }
        if (field instanceof RangeDateField rangeDateField) {
            return Mono.just(Criteria.where(rangeDateField.getField()).lte(rangeDateField.getMax()).gte(rangeDateField.getMin()));
        }
        if (field instanceof ListExactField listExactField) {
            return buildListCriteria(listExactField);
        }
        if (field instanceof ListMatchField listMatchField) {
            return buildListCriteria(listMatchField);
        }
        if (field instanceof ListRangeField listRangeField) {
            return buildListCriteria(listRangeField);
        }
        if (field instanceof ListRangeDateField listRangeDateField) {
            return buildListCriteria(listRangeDateField);
        }
        return Mono.error(FieldTypeNotSupportedException::new);
    }

    private Mono<Criteria> buildListCriteria(Field field) {
        return build(field).map(Criteria.where(field.getField())::elemMatch);
    }


}
