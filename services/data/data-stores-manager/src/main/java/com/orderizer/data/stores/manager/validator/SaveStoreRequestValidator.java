package com.orderizer.data.stores.manager.validator;

import com.orderizer.data.stores.manager.model.request.SaveStoreRequest;
import com.selling.system.shared.module.api.Validator;
import com.selling.system.shared.module.exceptions.Technical.RequestBodyInvalidException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.selling.system.shared.module.utils.StringUtil.isEmpty;

@Component
public class SaveStoreRequestValidator implements Validator<SaveStoreRequest> {
    @Override
    public Mono<SaveStoreRequest> validate(SaveStoreRequest request) {
        return Mono.defer(() -> {
            if(isEmpty(request.getStoreLocation())) {
                return Mono.error(new RequestBodyInvalidException("store location can not be empty"));
            }
            return Mono.just(request);
        });
    }
}
