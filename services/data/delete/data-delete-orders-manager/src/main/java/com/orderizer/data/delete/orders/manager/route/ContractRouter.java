package com.orderizer.data.delete.orders.manager.route;

import com.orderizer.data.delete.orders.manager.handler.*;
import com.orderizer.data.delete.orders.manager.model.request.DeleteOrdersByGlobalIdentifiersRequest;
import com.orderizer.data.delete.orders.manager.model.request.DeleteOrdersByLocalIdentifiersRequest;
import com.orderizer.data.delete.orders.manager.model.request.DeleteOrdersSearchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Map;

public interface ContractRouter {


    @RouterOperations({
            @RouterOperation(path = "/local", method = RequestMethod.DELETE, beanClass = DeleteOrderByLocalIdentifierHandler.class, beanMethod = "handle", operation = @Operation(
                    operationId = "DeleteOrderByLocalIdentifierHandler", parameters = {
                    @Parameter(name = "local-identifier", in = ParameterIn.QUERY),
                    @Parameter(name = "store-location", in = ParameterIn.QUERY)})),
            @RouterOperation(path = "/global", method = RequestMethod.DELETE, beanClass = DeleteOrderByGlobalIdentifierHandler.class, beanMethod = "handle", operation = @Operation(
                    operationId = "DeleteOrderByGlobalIdentifierHandler", parameters = {
                    @Parameter(name = "global-identifier", in = ParameterIn.QUERY)})),
            @RouterOperation(path = "/batch/local", method = RequestMethod.DELETE, beanClass = DeleteOrdersByLocalIdentifierHandler.class, beanMethod = "handle", operation = @Operation(
                    operationId = "DeleteOrderByLocalIdentifierHandler", requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = DeleteOrdersByLocalIdentifiersRequest.class))))),
            @RouterOperation(path = "/batch/global", method = RequestMethod.DELETE, beanClass = DeleteOrdersByGlobalIdentifierHandler.class, beanMethod = "handle", operation = @Operation(
                    operationId = "DeleteOrderByGlobalIdentifierHandler", requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = DeleteOrdersByGlobalIdentifiersRequest.class))))),
            @RouterOperation(path = "/search", method = RequestMethod.DELETE, beanClass = DeleteOrdersSearchHandler.class, beanMethod = "handle", operation = @Operation(
                    operationId = "DeleteOrdersSearchHandler", requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = DeleteOrdersSearchRequest.class)))))
    })
    RouterFunction<ServerResponse> appRoute(Map<String, HandlerFunction<ServerResponse>> handlers);
}
