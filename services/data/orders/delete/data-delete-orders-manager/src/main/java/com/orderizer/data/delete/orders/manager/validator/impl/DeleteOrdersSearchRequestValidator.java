package com.orderizer.data.delete.orders.manager.validator.impl;

import com.orderizer.data.delete.orders.manager.model.request.DeleteOrdersSearchRequest;
import com.orderizer.data.delete.orders.manager.validator.api.Validator;
import com.orderizer.core.exceptions.Technical.RequestBodyInvalidException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.orderizer.core.utils.CollectionUtil.isEmpty;

@Component
public class DeleteOrdersSearchRequestValidator implements Validator<DeleteOrdersSearchRequest> {
    @Override
    public Mono<DeleteOrdersSearchRequest> validate(DeleteOrdersSearchRequest deleteOrdersSearchRequest) {
        return Mono.defer(() -> {
            if (isEmpty(deleteOrdersSearchRequest.getExactFields()) &&
                    isEmpty(deleteOrdersSearchRequest.getMatchFields()) &&
                    isEmpty(deleteOrdersSearchRequest.getRangeDateFields()) &&
                    isEmpty(deleteOrdersSearchRequest.getRangeFields()) &&
                    isEmpty(deleteOrdersSearchRequest.getListExactFields()) &&
                    isEmpty(deleteOrdersSearchRequest.getListMatchFields()) &&
                    isEmpty(deleteOrdersSearchRequest.getListRangeDateFields()) &&
                    isEmpty(deleteOrdersSearchRequest.getListRangeFields())
            ) {
                return Mono.error(new RequestBodyInvalidException("at least one filed should be specified."));
            }
            return Mono.just(deleteOrdersSearchRequest);
        });
    }
}
