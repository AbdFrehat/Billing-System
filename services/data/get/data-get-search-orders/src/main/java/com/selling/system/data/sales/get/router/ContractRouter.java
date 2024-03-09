package com.selling.system.data.sales.get.router;

import com.selling.system.data.sales.get.handler.DataGetHandler;
import com.selling.system.data.sales.get.model.request.OrdersGetRequest;
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
                    method = RequestMethod.POST, beanClass = DataGetHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "DataGetHandler", requestBody = @RequestBody(
                            content = @Content(schema = @Schema(implementation = OrdersGetRequest.class))),
                            parameters = {@Parameter(in = ParameterIn.QUERY, name = "count")})
            )
    })
    RouterFunction<ServerResponse> dataGetRoute(Map<String, HandlerFunction<ServerResponse>> handlers);
}
