package com.orderizer.data.save.orders.router;

import com.orderizer.data.save.orders.handler.SaveOrdersHandler;
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
    public RouterFunction<ServerResponse> appRoute(SaveOrdersHandler saveOrdersHandler) {
        return route().POST(path("/"), saveOrdersHandler).build();
    }
}
