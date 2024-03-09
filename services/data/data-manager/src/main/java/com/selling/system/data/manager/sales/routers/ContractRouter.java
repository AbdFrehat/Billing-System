package com.selling.system.data.manager.sales.routers;

import com.selling.system.data.manager.sales.handlers.delete.DataDeleteHandler;
import com.selling.system.data.manager.sales.handlers.delete.DataDeleteMultiHandler;
import com.selling.system.data.manager.sales.handlers.delete.DataDeleteQueryHandler;
import com.selling.system.data.manager.sales.handlers.get.*;
import com.selling.system.data.manager.sales.handlers.save.DataSaveHandler;
import com.selling.system.data.manager.sales.handlers.save.DataSaveMultiHandler;
import com.selling.system.data.manager.sales.handlers.update.DataUpdateHandler;
import com.selling.system.data.manager.sales.handlers.update.DataUpdateMultiHandler;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
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
            @RouterOperation(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DataGetHandler.class, beanMethod = "handle",
                    operation = @Operation(operationId = "DataGetHandler")
            ),
            @RouterOperation(path = "/get/count", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DataGetCountHandler.class, beanMethod = "handle"
            ),
            @RouterOperation(path = "/get/free", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DataGetFreeHandler.class, beanMethod = "handle"
            ),
            @RouterOperation(path = "/get/free/count", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DataGetFreeCountHandler.class, beanMethod = "handle"
            ),
            @RouterOperation(path = "/get/opt", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DataGetOptHandler.class, beanMethod = "handle"
            ),
            @RouterOperation(path = "/get/opt/count", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DataGetOptCountHandler.class, beanMethod = "handle"
            ),
            @RouterOperation(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DataSaveHandler.class, beanMethod = "handle"
            ),
            @RouterOperation(path = "/save/mutli", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DataSaveMultiHandler.class, beanMethod = "handle"
            ),
            @RouterOperation(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DataUpdateHandler.class, beanMethod = "handle"
            ),
            @RouterOperation(path = "/update/multi", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DataUpdateMultiHandler.class, beanMethod = "handle"
            ),
            @RouterOperation(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DataDeleteHandler.class, beanMethod = "handle"
            ),
            @RouterOperation(path = "/delete/multi", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DataDeleteMultiHandler.class, beanMethod = "handle"
            ),
            @RouterOperation(path = "/delete/query", produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.POST, beanClass = DataDeleteQueryHandler.class, beanMethod = "handle"
            )

    })
    RouterFunction<ServerResponse> dataManagerRoute(Map<String, HandlerFunction<ServerResponse>> handlers);
}
