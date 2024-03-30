package com.orderizer.data.stores.manager.router.api;

import com.orderizer.data.stores.manager.handler.DeleteStoreHandler;
import com.orderizer.data.stores.manager.handler.GetStoresHandler;
import com.orderizer.data.stores.manager.handler.SaveStoreHandler;
import com.orderizer.data.stores.manager.handler.UpdateStoreHandler;
import com.orderizer.data.stores.manager.model.request.SaveStoreRequest;
import com.orderizer.data.stores.manager.model.request.UpdateStoreRequest;
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
            @RouterOperation(path = "/", method = RequestMethod.GET, beanClass = GetStoresHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "GetStoresHandler")),
            @RouterOperation(path = "/", method = RequestMethod.POST, beanClass = SaveStoreHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "SaveStoresHandler", requestBody =
                    @RequestBody(content = @Content(schema = @Schema(implementation = SaveStoreRequest.class))))),
            @RouterOperation(path = "/", method = RequestMethod.PATCH, beanClass = UpdateStoreHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "UpdateStoresHandler", parameters = {
                            @Parameter(name = "identifier", in = ParameterIn.QUERY)
                    }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = UpdateStoreRequest.class))))),
            @RouterOperation(path = "/", method = RequestMethod.DELETE, beanClass = DeleteStoreHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "DeleteStoresHandler", parameters = {
                            @Parameter(name = "identifier", in = ParameterIn.QUERY)
                    }))
    })
    RouterFunction<ServerResponse> appRoute(Map<String, HandlerFunction<ServerResponse>> handlers);
}
