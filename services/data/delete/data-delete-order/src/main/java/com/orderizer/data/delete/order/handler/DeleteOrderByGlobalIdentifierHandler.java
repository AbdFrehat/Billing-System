package com.orderizer.data.delete.order.handler;

import com.orderizer.data.delete.order.repository.api.OrdersRepository;
import com.selling.system.shared.module.utils.QueryParamsUtil;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DeleteOrderByGlobalIdentifierHandler implements HandlerFunction<ServerResponse> {

    private final OrdersRepository ordersRepository;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return QueryParamsUtil.getQueryParam(request, "global-identifier", Long::parseLong)
                .map(ordersRepository::deleteOrderByGlobalIdentifier)
                .flatMap(deleteResult -> ServerResponse.noContent().build());
    }
}
