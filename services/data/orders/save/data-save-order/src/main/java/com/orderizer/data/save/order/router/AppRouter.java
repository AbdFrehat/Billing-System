package com.orderizer.data.save.order.router;

import com.orderizer.data.save.order.handler.SaveOrderHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AppRouter implements ContractRouter {

    @Bean
    public RouterFunction<ServerResponse> appRoute(SaveOrderHandler saveOrderHandler) {
        return route().POST(path("/"), saveOrderHandler).build();
    }
}
