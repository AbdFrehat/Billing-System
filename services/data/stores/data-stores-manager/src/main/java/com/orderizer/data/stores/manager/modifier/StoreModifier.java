package com.orderizer.data.stores.manager.modifier;

import com.orderizer.data.stores.manager.model.entity.Store;
import com.orderizer.data.stores.manager.model.request.UpdateStoreRequest;
import com.selling.system.shared.module.api.Modifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class StoreModifier implements Modifier<UpdateStoreRequest, Store> {
    @Override
    public Mono<Store> modify(UpdateStoreRequest request, Store store) {
        return Mono.defer(() -> {
            store.setStoreLocation(request.getStoreLocation());
            return Mono.just(store);
        });
    }
}
