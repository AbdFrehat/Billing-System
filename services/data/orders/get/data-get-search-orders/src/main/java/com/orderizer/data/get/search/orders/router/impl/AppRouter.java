package com.orderizer.data.get.search.orders.router.impl;

import com.orderizer.data.get.search.orders.handler.GetOrderByGlobalIdentifierHandler;
import com.orderizer.data.get.search.orders.handler.GetOrderByLocalIdentifierHandler;
import com.orderizer.data.get.search.orders.handler.GetOrdersCountHandler;
import com.orderizer.data.get.search.orders.handler.GetOrdersSearchHandler;
import com.orderizer.data.get.search.orders.router.api.ContractRouter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Map;

import static com.orderizer.core.utils.BeansUtil.extract;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AppRouter implements ContractRouter {


    @Bean
    public RouterFunction<ServerResponse> dataGetRoute(Map<String, HandlerFunction<ServerResponse>> handlers) {
        return route().POST(path("/"), extract(handlers, GetOrdersSearchHandler.class))
                .POST(path("/count"), extract(handlers, GetOrdersCountHandler.class))
                .GET(path("/local"), extract(handlers, GetOrderByLocalIdentifierHandler.class))
                .GET(path("/global"), extract(handlers, GetOrderByGlobalIdentifierHandler.class))
                .build();
    }
}
