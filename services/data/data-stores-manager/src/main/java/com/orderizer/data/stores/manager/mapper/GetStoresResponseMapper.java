package com.orderizer.data.stores.manager.mapper;

import com.orderizer.data.stores.manager.model.response.GetStoresResponse;
import com.orderizer.data.stores.manager.model.response.StoreResponse;
import com.selling.system.shared.module.api.Mapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class GetStoresResponseMapper implements Mapper<List<StoreResponse>, GetStoresResponse> {
    @Override
    public Mono<GetStoresResponse> map(List<StoreResponse> storeResponses) {
        return Mono.just(GetStoresResponse.builder()
                .stores(storeResponses)
                .build());
    }
}
