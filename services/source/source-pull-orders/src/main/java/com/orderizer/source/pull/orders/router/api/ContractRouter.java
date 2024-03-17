package com.orderizer.source.pull.orders.router.api;

import com.orderizer.source.pull.orders.handler.PullSaveOrdersRequestHandler;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

public interface ContractRouter {


    @RouterOperation(path = "/save/order/request", produces = MediaType.TEXT_EVENT_STREAM_VALUE, beanClass = PullSaveOrdersRequestHandler.class
            , beanMethod = "handle", operation = @Operation(operationId = "PullSaveOrdersRequestHandler"))
    RouterFunction<ServerResponse> appRoute(PullSaveOrdersRequestHandler pullSaveOrdersRequestHandler);
}
