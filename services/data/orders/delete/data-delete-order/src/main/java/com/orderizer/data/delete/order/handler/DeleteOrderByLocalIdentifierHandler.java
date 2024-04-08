package com.orderizer.data.delete.order.handler;

import com.orderizer.data.delete.order.repository.api.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static com.orderizer.core.utils.QueryParamsUtil.getQueryParam;

@Component
@RequiredArgsConstructor
public class DeleteOrderByLocalIdentifierHandler implements HandlerFunction<ServerResponse> {

    private final OrdersRepository ordersRepository;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return Mono.zip(getQueryParam(request, "local-identifier", Long::parseLong), getQueryParam(request, "store-location", Function.identity()))
                .flatMap(tuple -> ordersRepository.deleteOrderByLocalIdentifier(tuple.getT1(), tuple.getT2()))
                .then(ServerResponse.noContent().build());
    }

}
