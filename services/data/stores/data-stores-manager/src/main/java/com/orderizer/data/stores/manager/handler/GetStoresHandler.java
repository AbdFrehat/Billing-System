package com.orderizer.data.stores.manager.handler;

import com.orderizer.data.stores.manager.model.entity.Store;
import com.orderizer.data.stores.manager.model.response.GetStoresResponse;
import com.orderizer.data.stores.manager.model.response.StoreResponse;
import com.orderizer.data.stores.manager.repository.api.StoresRepository;
import com.orderizer.core.api.Mapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetStoresHandler implements HandlerFunction<ServerResponse> {

    private final StoresRepository storesRepository;
    private final Mapper<Store, StoreResponse> mapper1;
    private final Mapper<List<StoreResponse>, GetStoresResponse> mapper2;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return storesRepository.retrieveStores()
                .flatMap(mapper1::map)
                .collectList()
                .flatMap(mapper2::map)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

}
