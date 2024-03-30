package com.orderizer.data.update.orders.manager.router.api;

import com.orderizer.data.update.orders.manager.handler.UpdateOrderHandler;
import com.orderizer.data.update.orders.manager.handler.UpdateOrdersHandler;
import com.orderizer.data.update.orders.manager.model.request.OrderUpdateRequest;
import com.orderizer.data.update.orders.manager.model.request.OrdersUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

public interface ContractRouter {


    @RouterOperations({
            @RouterOperation(path = "/", method = RequestMethod.PATCH, beanClass = UpdateOrderHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "UpdateOrderHandler", parameters = {
                            @Parameter(name = "global-identifier", in = ParameterIn.QUERY)
                    }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = OrderUpdateRequest.class))))),
            @RouterOperation(path = "/batch", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE,
                    beanClass = UpdateOrdersHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "UpdateOrdersHandler",
                            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = OrdersUpdateRequest.class)))))
    })
    RouterFunction<ServerResponse> appRoute(UpdateOrderHandler updateOrderHandler, UpdateOrdersHandler updateOrdersHandler);
}
