package com.orderizer.data.save.order.router;

import com.orderizer.data.save.order.handler.SaveOrderHandler;
import com.orderizer.data.save.order.model.request.OrderSaveRequest;
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

    @RouterOperation(path = "/", produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST, beanClass = SaveOrderHandler.class, beanMethod = "handle",
            operation = @Operation(operationId = "SaveOrderHandler", requestBody = @RequestBody(
                    content = @Content(schema = @Schema(implementation = OrderSaveRequest.class))
            )))
    RouterFunction<ServerResponse> appRoute(SaveOrderHandler saveOrderHandler);
}
