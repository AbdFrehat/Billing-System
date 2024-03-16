package com.orderizer.data.update.order.router.impl;

import com.orderizer.data.update.order.handler.UpdateOrderHandler;
import com.orderizer.data.update.order.router.api.ContractRouter;
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
    public RouterFunction<ServerResponse> appRoute(UpdateOrderHandler updateOrderHandler) {
        return route().PATCH(path("/"), updateOrderHandler).build();
    }
}
