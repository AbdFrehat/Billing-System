package com.orderizer.data.orders.manager.router.impl;

import com.orderizer.data.orders.manager.handler.delete.DeleteOrderByGlobalIdentifierHandler;
import com.orderizer.data.orders.manager.handler.delete.DeleteOrdersByGlobalIdentifierHandler;
import com.orderizer.data.orders.manager.handler.delete.DeleteOrdersByLocalIdentifierHandler;
import com.orderizer.data.orders.manager.handler.delete.DeleteOrdersSearchHandler;
import com.orderizer.data.orders.manager.handler.get.GetOrderByGlobalIdentifierHandler;
import com.orderizer.data.orders.manager.handler.get.GetOrderByLocalIdentifierHandler;
import com.orderizer.data.orders.manager.handler.get.GetOrdersCountHandler;
import com.orderizer.data.orders.manager.handler.get.GetOrdersSearchHandler;
import com.orderizer.data.orders.manager.handler.save.SaveOrderHandler;
import com.orderizer.data.orders.manager.handler.update.UpdateOrderHandler;
import com.orderizer.data.orders.manager.handler.update.UpdateOrdersHandler;
import com.orderizer.data.orders.manager.router.api.ContractRouter;
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
        return route().nest(path(""), nestL1 -> nestL1.nest(path("/get"), nestL2 ->
                                nestL2.nest(path("/search"), nestL3 ->
                                                nestL3.POST("/", extract(handlers, GetOrdersSearchHandler.class))
                                                        .POST("/count", extract(handlers, GetOrdersCountHandler.class))
                                                        .GET("/local", extract(handlers, GetOrderByLocalIdentifierHandler.class))
                                                        .GET("/global", extract(handlers, GetOrderByGlobalIdentifierHandler.class))
                                                        .build())
                                        .build())
                        .nest(path("/delete"), nestL2 ->
                                nestL2.DELETE(path("/local"), extract(handlers, DeleteOrderByGlobalIdentifierHandler.class))
                                        .DELETE(path("/global"), extract(handlers, DeleteOrderByGlobalIdentifierHandler.class))
                                        .nest(path("/batch"), nestL3 ->
                                                nestL3.DELETE(path("/local"), extract(handlers, DeleteOrdersByLocalIdentifierHandler.class))
                                                        .DELETE(path("/global"), extract(handlers, DeleteOrdersByGlobalIdentifierHandler.class))
                                                        .build())
                                        .POST(path("/search"), extract(handlers, DeleteOrdersSearchHandler.class))
                                        .build())
                        .nest(path("/update"), nestL2 ->
                                nestL2.PATCH(path(""), extract(handlers, UpdateOrderHandler.class))
                                        .PATCH(path("/batch"), extract(handlers, UpdateOrdersHandler.class))
                                        .build())
                        .nest(path("/save"), nestL2 ->
                                nestL2.POST(path(""), extract(handlers, SaveOrderHandler.class))
                                        .POST(path("/batch"), extract(handlers, UpdateOrdersHandler.class))
                                        .build())
                        .build())
                .build();
    }

}
