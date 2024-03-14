package com.orderizer.data.delete.order.route;

import com.orderizer.data.delete.order.handler.DeleteOrderByGlobalIdentifierHandler;
import com.orderizer.data.delete.order.handler.DeleteOrderByLocalIdentifierHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

public interface ContractRouter {

    @RouterOperations({
            @RouterOperation(path = "/global", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.DELETE, beanClass = DeleteOrderByGlobalIdentifierHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "DeleteOrderByGlobalIdentifierHandler", parameters = {
                            @Parameter(in = ParameterIn.QUERY, name = "global-identifier")
                    })),
            @RouterOperation(path = "/local", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.DELETE, beanClass = DeleteOrderByLocalIdentifierHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "DeleteOrderByLocalIdentifierHandler", parameters = {
                            @Parameter(in = ParameterIn.QUERY, name = "local-identifier"),
                            @Parameter(in = ParameterIn.QUERY, name = "store-location")
                    }))
    })
    RouterFunction<ServerResponse> appRoute(DeleteOrderByGlobalIdentifierHandler deleteOrderByGlobalIdentifierHandler,
                                            DeleteOrderByLocalIdentifierHandler deleteOrderByLocalIdentifierHandler);
}
