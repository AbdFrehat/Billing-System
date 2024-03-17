package com.orderizer.source.pull.orders.router.impl;

import com.orderizer.source.pull.orders.handler.PullSaveOrdersRequestHandler;
import com.orderizer.source.pull.orders.router.api.ContractRouter;
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
    public RouterFunction<ServerResponse> appRoute(PullSaveOrdersRequestHandler pullSaveOrdersRequestHandler) {
        return route().GET(path("/save/order/request"), pullSaveOrdersRequestHandler).build();
    }
}
