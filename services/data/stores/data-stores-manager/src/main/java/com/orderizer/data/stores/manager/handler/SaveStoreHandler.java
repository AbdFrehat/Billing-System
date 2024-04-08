package com.orderizer.data.stores.manager.handler;

import com.orderizer.data.stores.manager.model.entity.Store;
import com.orderizer.data.stores.manager.model.request.SaveStoreRequest;
import com.orderizer.data.stores.manager.model.response.StoreResponse;
import com.orderizer.data.stores.manager.repository.api.StoresRepository;
import com.orderizer.core.api.Mapper;
import com.orderizer.core.api.Validator;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SaveStoreHandler implements HandlerFunction<ServerResponse> {

    private final StoresRepository storesRepository;

    private final Mapper<Store, StoreResponse> mapper;

    private final Validator<SaveStoreRequest> validator;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(SaveStoreRequest.class)
                .flatMap(validator::validate)
                .flatMap(storesRepository::saveStore)
                .flatMap(mapper::map)
                .flatMap(body -> ServerResponse.status(HttpStatus.CREATED).bodyValue(body));
    }
}
