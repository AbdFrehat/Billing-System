package com.orderizer.data.delete.search.orders.route;

import com.orderizer.data.delete.search.orders.handler.DeleteOrdersSearchHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AppRouter implements ContractRouter {

    @Override
    @Bean
    public RouterFunction<ServerResponse> appRoute(DeleteOrdersSearchHandler deleteOrdersSearchHandler) {
        return route().DELETE("/", deleteOrdersSearchHandler).build();
    }
}
