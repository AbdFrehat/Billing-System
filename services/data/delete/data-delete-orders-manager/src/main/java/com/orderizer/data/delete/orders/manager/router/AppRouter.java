package com.orderizer.data.delete.orders.manager.router;

import com.orderizer.data.delete.orders.manager.handler.*;
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
    @Override
    public RouterFunction<ServerResponse> appRoute(Map<String, HandlerFunction<ServerResponse>> handlers) {
        return route().nest(path("/"), nestL1 -> nestL1.DELETE(path("global"), extract(handlers, DeleteOrderByGlobalIdentifierHandler.class))
                        .DELETE(path("local"), extract(handlers, DeleteOrderByLocalIdentifierHandler.class))
                        .nest(path("batch"), nestL2 -> nestL2.DELETE(path("global"), extract(handlers, DeleteOrdersByGlobalIdentifierHandler.class))
                                .DELETE(path("local"), extract(handlers, DeleteOrdersByLocalIdentifierHandler.class)).build())
                        .DELETE(path("search"), extract(handlers, DeleteOrdersSearchHandler.class))
                        .build())
                .build();
    }
}
