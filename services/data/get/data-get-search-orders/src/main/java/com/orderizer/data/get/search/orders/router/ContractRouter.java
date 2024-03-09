package com.orderizer.data.get.search.orders.router;

import com.orderizer.data.get.search.orders.handler.GetOrderByGlobalIdentifierHandler;
import com.orderizer.data.get.search.orders.handler.GetOrderByLocalIdentifierHandler;
import com.orderizer.data.get.search.orders.handler.GetOrdersSearchHandler;
import com.orderizer.data.get.search.orders.model.request.OrdersGetRequest;
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
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Map;

public interface ContractRouter {


    @RouterOperations({
            @RouterOperation(path = "/", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = GetOrdersSearchHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "GetOrdersSearchHandler", requestBody = @RequestBody(
                            content = @Content(schema = @Schema(implementation = OrdersGetRequest.class))),
                            parameters = {
                                    @Parameter(in = ParameterIn.QUERY, name = "count")
                            })
            ),
            @RouterOperation(path = "/local", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET, beanClass = GetOrderByLocalIdentifierHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "GetOrderByLocalIdentifierHandler",
                            parameters = {
                                    @Parameter(in = ParameterIn.QUERY, name = "local-identifier"),
                                    @Parameter(in = ParameterIn.QUERY, name = "store-location"),
                            })
            ),
            @RouterOperation(path = "/global", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET, beanClass = GetOrderByGlobalIdentifierHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "GetOrderByGlobalIdentifierHandler",
                            parameters = {
                                    @Parameter(in = ParameterIn.QUERY, name = "global-identifier")
                            })
            )
    })
    RouterFunction<ServerResponse> dataGetRoute(Map<String, HandlerFunction<ServerResponse>> handlers);
}
