package com.orderizer.data.delete.orders.router;

import com.orderizer.data.delete.orders.handler.DeleteOrdersByGlobalIdentifierHandler;
import com.orderizer.data.delete.orders.handler.DeleteOrdersByLocalIdentifierHandler;
import com.orderizer.data.delete.orders.model.request.DeleteOrdersByGlobalIdentifiersRequest;
import com.orderizer.data.delete.orders.model.request.DeleteOrdersByLocalIdentifiersRequest;
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
            @RouterOperation(path = "/global", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DeleteOrdersByGlobalIdentifierHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "DeleteOrdersByGlobalIdentifierHandler", requestBody = @RequestBody(
                            content = @Content(schema = @Schema(implementation = DeleteOrdersByGlobalIdentifiersRequest.class))
                    ))),
            @RouterOperation(path = "/local", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DeleteOrdersByLocalIdentifierHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "DeleteOrdersByLocalIdentifierHandler", requestBody = @RequestBody(
                            content = @Content(schema = @Schema(implementation = DeleteOrdersByLocalIdentifiersRequest.class))
                    )))
    })
    RouterFunction<ServerResponse> appRoute(DeleteOrdersByLocalIdentifierHandler deleteOrdersByLocalIdentifierHandler,
                                            DeleteOrdersByGlobalIdentifierHandler deleteOrdersByGlobalIdentifierHandler);
}
