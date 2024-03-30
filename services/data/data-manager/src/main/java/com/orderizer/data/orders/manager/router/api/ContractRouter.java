package com.orderizer.data.orders.manager.router.api;

import com.orderizer.data.orders.manager.handler.orders.delete.*;
import com.orderizer.data.orders.manager.handler.orders.get.GetOrderByGlobalIdentifierHandler;
import com.orderizer.data.orders.manager.handler.orders.get.GetOrderByLocalIdentifierHandler;
import com.orderizer.data.orders.manager.handler.orders.get.GetOrdersCountHandler;
import com.orderizer.data.orders.manager.handler.orders.get.GetOrdersSearchHandler;
import com.orderizer.data.orders.manager.handler.orders.save.SaveOrderHandler;
import com.orderizer.data.orders.manager.handler.orders.save.SaveOrdersHandler;
import com.orderizer.data.orders.manager.handler.orders.update.UpdateOrderHandler;
import com.orderizer.data.orders.manager.handler.orders.update.UpdateOrdersHandler;
import com.orderizer.data.orders.manager.model.request.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
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

@OpenAPIDefinition(info = @Info(title = "Data Manager", version = "1.0", description = "Documentation APIs v1.0"))
public interface ContractRouter {

    @RouterOperations({
            @RouterOperation(path = "/orders/delete/global", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DeleteOrderByGlobalIdentifierHandler.class, beanMethod = "handle",
                    operation = @Operation(
                            operationId = "DeleteOrderByGlobalIdentifierHandler", parameters = {
                            @Parameter(name = "global-identifier", in = ParameterIn.QUERY)})
            ),
            @RouterOperation(path = "/orders/delete/local", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DeleteOrderByLocalIdentifierHandler.class, beanMethod = "handle",
                    operation = @Operation(
                            operationId = "DeleteOrderByLocalIdentifierHandler", parameters = {
                            @Parameter(name = "local-identifier", in = ParameterIn.QUERY),
                            @Parameter(name = "store-location", in = ParameterIn.QUERY)})
            ),
            @RouterOperation(path = "/orders/delete/batch/global", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DeleteOrdersByGlobalIdentifierHandler.class, beanMethod = "handle",
                    operation = @Operation(
                            operationId = "DeleteOrderByGlobalIdentifierHandler", requestBody = @RequestBody(content =
                    @Content(schema = @Schema(implementation = DeleteOrdersByGlobalIdentifiersRequest.class))))
            ),
            @RouterOperation(path = "/orders/delete/batch/local", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DeleteOrdersByLocalIdentifierHandler.class, beanMethod = "handle",
                    operation = @Operation(
                            operationId = "DeleteOrderByLocalIdentifierHandler", requestBody = @RequestBody(content =
                    @Content(schema = @Schema(implementation = DeleteOrdersByLocalIdentifiersRequest.class))))
            ),
            @RouterOperation(path = "/orders/delete/search", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DeleteOrdersSearchHandler.class, beanMethod = "handle",
                    operation = @Operation(
                            operationId = "DeleteOrdersSearchHandler", requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = DeleteOrdersSearchRequest.class))))
            )
    })
    RouterFunction<ServerResponse> deleteOrdersRoute(Map<String, HandlerFunction<ServerResponse>> handlers);

    @RouterOperations({
            @RouterOperation(path = "/orders/get/search", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = GetOrdersSearchHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "GetOrdersSearchHandler", requestBody = @RequestBody(
                            content = @Content(schema = @Schema(implementation = OrdersGetRequest.class))))
            ),
            @RouterOperation(path = "/orders/get/search/count", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = GetOrdersCountHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "GetOrdersCountHandler", requestBody = @RequestBody(
                            content = @Content(schema = @Schema(implementation = OrdersGetRequest.class))),
                            parameters = {
                                    @Parameter(in = ParameterIn.QUERY, name = "count")
                            })
            ),
            @RouterOperation(path = "/orders/get/search/local", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET, beanClass = GetOrderByLocalIdentifierHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "GetOrderByLocalIdentifierHandler",
                            parameters = {
                                    @Parameter(in = ParameterIn.QUERY, name = "local-identifier"),
                                    @Parameter(in = ParameterIn.QUERY, name = "store-location"),
                            })
            ),
            @RouterOperation(path = "/orders/get/search/global", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET, beanClass = GetOrderByGlobalIdentifierHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "GetOrderByGlobalIdentifierHandler",
                            parameters = {
                                    @Parameter(in = ParameterIn.QUERY, name = "global-identifier")
                            })
            )
    })
    RouterFunction<ServerResponse> getOrdersRoute(Map<String, HandlerFunction<ServerResponse>> handlers);

    @RouterOperations({
            @RouterOperation(path = "/orders/save", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = SaveOrderHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "SaveOrderHandler", requestBody = @RequestBody(
                            content = @Content(schema = @Schema(implementation = OrderSaveRequest.class))
                    ))
            ),
            @RouterOperation(path = "/orders/save/batch", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = SaveOrdersHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "SaveOrdersHandler", requestBody = @RequestBody(
                            content = @Content(schema = @Schema(implementation = OrdersSaveRequest.class))
                    ))
            )
    })
    RouterFunction<ServerResponse> saveOrdersRoute(Map<String, HandlerFunction<ServerResponse>> handlers);

    @RouterOperations({
            @RouterOperation(path = "/orders/update", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = UpdateOrderHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "UpdateOrderHandler", parameters = {
                            @Parameter(name = "global-identifier", in = ParameterIn.QUERY)
                    }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = OrderUpdateRequest.class))))
            ),
            @RouterOperation(path = "/orders/update/batch", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = UpdateOrdersHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "UpdateOrdersHandler",
                            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = OrdersUpdateRequest.class))))
            )
    })
    RouterFunction<ServerResponse> updateOrdersRoute(Map<String, HandlerFunction<ServerResponse>> handlers);

}
