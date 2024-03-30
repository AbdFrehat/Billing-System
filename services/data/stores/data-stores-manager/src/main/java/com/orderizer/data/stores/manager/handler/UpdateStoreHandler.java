package com.orderizer.data.stores.manager.handler;

import com.orderizer.data.stores.manager.model.entity.Store;
import com.orderizer.data.stores.manager.model.request.UpdateStoreRequest;
import com.orderizer.data.stores.manager.model.response.StoreResponse;
import com.orderizer.data.stores.manager.repository.api.StoresRepository;
import com.selling.system.shared.module.api.Mapper;
import com.selling.system.shared.module.api.Modifier;
import com.selling.system.shared.module.api.Validator;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.selling.system.shared.module.utils.QueryParamsUtil.getQueryParam;

@Component
@RequiredArgsConstructor
public class UpdateStoreHandler implements HandlerFunction<ServerResponse> {

    private final StoresRepository storesRepository;

    private final Mapper<Store, StoreResponse> mapper;

    private final Modifier<UpdateStoreRequest, Store> modifier;

    private final Validator<UpdateStoreRequest> validator;


    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return request.bodyToMono(UpdateStoreRequest.class)
                .flatMap(validator::validate)
                .flatMap(updateStoreRequest ->
                        getQueryParam(request, "identifier", Integer::parseInt)
                                .flatMap(storesRepository::retrieveStoreByIdentifier)
                                .flatMap(store -> modifier.modify(updateStoreRequest, store))
                                .flatMap(storesRepository::updateStore)
                                .flatMap(mapper::map)
                                .flatMap(storeResponse -> ServerResponse.accepted().bodyValue(storeResponse)));
    }
}
