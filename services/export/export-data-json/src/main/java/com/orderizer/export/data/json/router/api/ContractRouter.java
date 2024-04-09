package com.orderizer.export.data.json.router.api;

import com.orderizer.export.data.json.handler.ExportOrdersHandler;
import com.orderizer.export.data.json.model.request.ExportOrdersFilterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

public interface ContractRouter {


    @RouterOperation(path = "/orders", method = RequestMethod.POST,
            beanClass = ExportOrdersHandler.class, beanMethod = "handle",
            operation = @Operation(operationId = "ExportOrdersHandler",
                    requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = ExportOrdersFilterRequest.class)))))
    RouterFunction<ServerResponse> appRoute(ExportOrdersHandler exportOrdersHandler);


}
