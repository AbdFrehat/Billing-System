package com.orderizer.data.update.orders.manager.router.impl;

import com.orderizer.data.update.orders.manager.handler.UpdateOrderHandler;
import com.orderizer.data.update.orders.manager.handler.UpdateOrdersHandler;
import com.orderizer.data.update.orders.manager.router.api.ContractRouter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AppRouter implements ContractRouter {
    @Bean
    @Override
    public RouterFunction<ServerResponse> appRoute(UpdateOrderHandler updateOrderHandler, UpdateOrdersHandler updateOrdersHandler) {
        return route().PATCH(path("/"), updateOrderHandler)
                .PATCH(path("/batch"), updateOrdersHandler).build();
    }
}
