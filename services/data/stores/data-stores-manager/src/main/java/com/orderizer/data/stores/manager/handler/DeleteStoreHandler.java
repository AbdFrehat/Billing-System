package com.orderizer.data.stores.manager.handler;

import com.orderizer.data.stores.manager.repository.api.StoresRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.orderizer.core.utils.QueryParamsUtil.getQueryParam;

@Component
@RequiredArgsConstructor
public class DeleteStoreHandler implements HandlerFunction<ServerResponse> {

    private final StoresRepository storesRepository;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return getQueryParam(request, "identifier", Integer::parseInt)
                .flatMap(identifier -> storesRepository.deleteStore(identifier)
                        .then(ServerResponse.noContent().build()));
    }
}
