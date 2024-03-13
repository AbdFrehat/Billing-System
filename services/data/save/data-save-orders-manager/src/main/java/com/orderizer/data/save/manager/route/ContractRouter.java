package com.orderizer.data.save.manager.route;

import com.orderizer.data.save.manager.handler.SaveOrderHandler;
import com.orderizer.data.save.manager.handler.SaveOrdersHandler;
import com.orderizer.data.save.manager.model.request.OrderSaveRequest;
import com.orderizer.data.save.manager.model.request.OrdersSaveRequest;
import io.swagger.v3.oas.annotations.Operation;
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
            @RouterOperation(path = "/", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = SaveOrderHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "SaveOrderHandler", requestBody = @RequestBody(
                            content = @Content(schema = @Schema(implementation = OrderSaveRequest.class))
                    ))),
            @RouterOperation(path = "/batch", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = SaveOrdersHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "SaveOrdersHandler", requestBody = @RequestBody(
                            content = @Content(schema = @Schema(implementation = OrdersSaveRequest.class))
                    )))
    })
    RouterFunction<ServerResponse> appRoute(SaveOrderHandler saveOrderHandler, SaveOrdersHandler saveOrdersHandler);
}
