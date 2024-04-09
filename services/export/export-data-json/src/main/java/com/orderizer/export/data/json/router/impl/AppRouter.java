package com.orderizer.export.data.json.router.impl;

import com.orderizer.export.data.json.handler.ExportOrdersHandler;
import com.orderizer.export.data.json.router.api.ContractRouter;
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
    public RouterFunction<ServerResponse> appRoute(ExportOrdersHandler exportOrdersHandler) {
        return route().POST(path("/orders"), exportOrdersHandler).build();
    }
}
