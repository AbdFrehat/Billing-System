package com.orderizer.data.update.orders.router.impl;

import com.orderizer.data.update.orders.handler.UpdateOrdersHandler;
import com.orderizer.data.update.orders.router.api.ContractRouter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AppRouter implements ContractRouter {
    @Override
    @Bean
    public RouterFunction<ServerResponse> appRoute(UpdateOrdersHandler updateOrdersHandler) {
        return route().PATCH(path("/"), updateOrdersHandler).build();
    }
}
