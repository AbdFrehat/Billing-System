package com.orderizer.data.delete.order.handler;

import com.orderizer.data.delete.order.repository.api.OrdersRepository;
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
public class DeleteOrderByGlobalIdentifierHandler implements HandlerFunction<ServerResponse> {

    private final OrdersRepository ordersRepository;

    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest request) {
        return getQueryParam(request, "global-identifier", Long::parseLong)
                .flatMap(ordersRepository::deleteOrderByGlobalIdentifier)
                .then(ServerResponse.noContent().build());
    }
}
