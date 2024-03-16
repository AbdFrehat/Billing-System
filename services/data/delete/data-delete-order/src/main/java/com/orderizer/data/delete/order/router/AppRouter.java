package com.orderizer.data.delete.order.router;

import com.orderizer.data.delete.order.handler.DeleteOrderByGlobalIdentifierHandler;
import com.orderizer.data.delete.order.handler.DeleteOrderByLocalIdentifierHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AppRouter implements ContractRouter {


    @Bean
    @Override
    public RouterFunction<ServerResponse> appRoute(DeleteOrderByGlobalIdentifierHandler deleteOrderByGlobalIdentifierHandler,
                                                   DeleteOrderByLocalIdentifierHandler deleteOrderByLocalIdentifierHandler) {
        return route().DELETE("/local", deleteOrderByLocalIdentifierHandler)
                .DELETE("global", deleteOrderByGlobalIdentifierHandler).build();
    }
}
