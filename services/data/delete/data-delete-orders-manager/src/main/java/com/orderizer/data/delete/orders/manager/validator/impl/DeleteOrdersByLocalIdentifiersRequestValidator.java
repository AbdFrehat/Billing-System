package com.orderizer.data.delete.orders.manager.validator.impl;

import com.orderizer.data.delete.orders.manager.model.request.DeleteOrdersByLocalIdentifiersRequest;
import com.orderizer.data.delete.orders.manager.validator.api.Validator;
import com.selling.system.shared.module.exceptions.Technical.RequestBodyInvalidException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.selling.system.shared.module.utils.CollectionUtil.isEmpty;

@Component
public class DeleteOrdersByLocalIdentifiersRequestValidator implements Validator<DeleteOrdersByLocalIdentifiersRequest> {
    @Override
    public Mono<DeleteOrdersByLocalIdentifiersRequest> validate(DeleteOrdersByLocalIdentifiersRequest deleteOrdersByLocalIdentifiersRequest) {
        return Mono.defer(() -> {
            if (isEmpty(deleteOrdersByLocalIdentifiersRequest.getLocalIdentifierCriteria())) {
                return Mono.error(new RequestBodyInvalidException("localIdentifierCriteria can not be empty"));
            }
            return Mono.just(deleteOrdersByLocalIdentifiersRequest);
        });
    }
}
