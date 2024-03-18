package com.orderizer.data.update.orders.router.api;

import com.orderizer.data.update.orders.handler.UpdateOrdersHandler;
import com.orderizer.data.update.orders.model.request.OrdersUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

public interface ContractRouter {

    @RouterOperation(path = "/", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = UpdateOrdersHandler.class, beanMethod = "handle",
            operation = @Operation(operationId = "UpdateOrdersHandler",
                    requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = OrdersUpdateRequest.class)))))
    RouterFunction<ServerResponse> appRoute(UpdateOrdersHandler updateOrdersHandler);
}
