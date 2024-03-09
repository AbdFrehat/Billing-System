package com.selling.system.data.manager.sales.routers;

import com.selling.system.data.manager.sales.handlers.delete.DataDeleteHandler;
import com.selling.system.data.manager.sales.handlers.delete.DataDeleteMultiHandler;
import com.selling.system.data.manager.sales.handlers.delete.DataDeleteQueryHandler;
import com.selling.system.data.manager.sales.handlers.get.*;
import com.selling.system.data.manager.sales.handlers.save.DataSaveHandler;
import com.selling.system.data.manager.sales.handlers.update.DataUpdateHandler;
import com.selling.system.data.manager.sales.handlers.update.DataUpdateMultiHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Map;

import static com.selling.system.shared.module.utils.BeansUtil.extract;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AppRouter implements ContractRouter {

    @Bean
    public RouterFunction<ServerResponse> dataManagerRoute(Map<String, HandlerFunction<ServerResponse>> handlers) {
        return route().nest(path(""),
                        nestL1 -> nestL1.nest(path("/get"), nestL2 -> nestL2
                                        .nest(path("/query"), nestL3 -> nestL3.POST("", extract(handlers, DataGetHandler.class))
                                                .POST("/count", extract(handlers, DataGetCountHandler.class))
                                                .build())
                                        .nest(path("/free"), nestL3 -> nestL3.POST("", extract(handlers, DataGetFreeHandler.class))
                                                .POST("/count", extract(handlers, DataGetFreeCountHandler.class))
                                                .build())
                                        .nest(path("/opt"), nestL3 -> nestL3.POST("", extract(handlers, DataGetOptHandler.class))
                                                .POST("/count", extract(handlers, DataGetOptCountHandler.class))
                                                .build())
                                        .build())
                                .nest(path("/delete"), nestL2 -> nestL2.POST(path(""), extract(handlers, DataDeleteHandler.class))
                                        .POST(path("/multi"), extract(handlers, DataDeleteMultiHandler.class))
                                        .POST(path("/query"), extract(handlers, DataDeleteQueryHandler.class))
                                        .build())
                                .nest(path("/update"), nestL2 -> nestL2.POST(path(""), extract(handlers, DataUpdateHandler.class))
                                        .POST(path("/multi"), extract(handlers, DataUpdateMultiHandler.class))
                                        .build())
                                .nest(path("/save"), nestL2 -> nestL2.POST(path(""), extract(handlers, DataSaveHandler.class))
                                        .POST(path("/multi"), extract(handlers, DataUpdateMultiHandler.class))
                                        .build())
                                .build())
                .build();
    }

}
