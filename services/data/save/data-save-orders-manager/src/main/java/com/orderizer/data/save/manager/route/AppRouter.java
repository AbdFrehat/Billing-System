package com.orderizer.data.save.manager.route;

import com.orderizer.data.save.manager.handler.SaveOrderHandler;
import com.orderizer.data.save.manager.handler.SaveOrdersHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AppRouter implements ContractRouter {


    @Bean
    public RouterFunction<ServerResponse> appRoute(SaveOrderHandler saveOrderHandler, SaveOrdersHandler saveOrdersHandler) {
        return route().POST("/", saveOrderHandler)
                .POST("/batch", saveOrdersHandler).build();
    }
}
