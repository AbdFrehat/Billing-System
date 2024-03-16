package com.orderizer.data.delete.orders.router;

import com.orderizer.data.delete.orders.handler.DeleteOrdersByGlobalIdentifierHandler;
import com.orderizer.data.delete.orders.handler.DeleteOrdersByLocalIdentifierHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AppRouter implements ContractRouter {

    @Bean
    @Override
    public RouterFunction<ServerResponse> appRoute(DeleteOrdersByLocalIdentifierHandler deleteOrdersByLocalIdentifierHandler,
                                                   DeleteOrdersByGlobalIdentifierHandler deleteOrdersByGlobalIdentifierHandler) {
        return route().POST("/global", deleteOrdersByGlobalIdentifierHandler)
                .POST("/local", deleteOrdersByLocalIdentifierHandler)
                .build();
    }
}
