package com.orderizer.data.stores.manager.mapper;

import com.orderizer.data.stores.manager.model.entity.Store;
import com.orderizer.data.stores.manager.model.response.StoreResponse;
import com.orderizer.core.api.Mapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetStoreResponseMapper implements Mapper<Store, StoreResponse> {

    @Override
    public Mono<StoreResponse> map(Store store) {
        return Mono.just(StoreResponse.builder()
                .storeLocation(store.getStoreLocation())
                .identifier(store.getIdentifier())
                .build());
    }
}
