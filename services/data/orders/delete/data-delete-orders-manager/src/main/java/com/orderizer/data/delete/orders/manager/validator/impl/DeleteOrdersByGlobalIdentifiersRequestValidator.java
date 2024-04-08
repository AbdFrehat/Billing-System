package com.orderizer.data.delete.orders.manager.validator.impl;

import com.orderizer.data.delete.orders.manager.model.request.DeleteOrdersByGlobalIdentifiersRequest;
import com.orderizer.data.delete.orders.manager.validator.api.Validator;
import com.orderizer.core.exceptions.Technical.RequestBodyInvalidException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.orderizer.core.utils.CollectionUtil.isEmpty;

@Component
public class DeleteOrdersByGlobalIdentifiersRequestValidator implements Validator<DeleteOrdersByGlobalIdentifiersRequest> {
    @Override
    public Mono<DeleteOrdersByGlobalIdentifiersRequest> validate(DeleteOrdersByGlobalIdentifiersRequest deleteOrdersByGlobalIdentifiersRequest) {
        return Mono.defer(() -> {
            if (isEmpty(deleteOrdersByGlobalIdentifiersRequest.getGlobalIdentifiers())) {
                return Mono.error(new RequestBodyInvalidException("globalIdentifiers can not be empty"));
            }
            return Mono.just(deleteOrdersByGlobalIdentifiersRequest);
        });
    }
}
