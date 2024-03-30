package com.orderizer.data.update.order.router.api;

import com.orderizer.data.update.order.handler.UpdateOrderHandler;
import com.orderizer.data.update.order.model.request.OrderUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

public interface ContractRouter {


    @RouterOperation(path = "/", method = RequestMethod.PATCH, beanClass = UpdateOrderHandler.class, beanMethod = "handle",
            operation = @Operation(operationId = "UpdateOrderHandler", requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = OrderUpdateRequest.class)))))
    RouterFunction<ServerResponse> appRoute(UpdateOrderHandler updateOrderHandler);
}
