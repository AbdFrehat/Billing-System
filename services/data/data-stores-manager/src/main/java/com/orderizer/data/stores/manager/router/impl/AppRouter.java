package com.orderizer.data.stores.manager.router.impl;

import com.orderizer.data.stores.manager.handler.DeleteStoreHandler;
import com.orderizer.data.stores.manager.handler.GetStoresHandler;
import com.orderizer.data.stores.manager.handler.SaveStoreHandler;
import com.orderizer.data.stores.manager.handler.UpdateStoreHandler;
import com.orderizer.data.stores.manager.router.api.ContractRouter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Map;

import static com.selling.system.shared.module.utils.BeansUtil.extract;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AppRouter implements ContractRouter {

    @Bean
    @Override
    public RouterFunction<ServerResponse> appRoute(Map<String, HandlerFunction<ServerResponse>> handlers) {
        return route().GET(path("/"), extract(handlers, GetStoresHandler.class))
                .POST(path("/"), extract(handlers, SaveStoreHandler.class))
                .PATCH(path("/"), extract(handlers, UpdateStoreHandler.class))
                .DELETE(path("/"), extract(handlers, DeleteStoreHandler.class))
                .build();
    }
}
