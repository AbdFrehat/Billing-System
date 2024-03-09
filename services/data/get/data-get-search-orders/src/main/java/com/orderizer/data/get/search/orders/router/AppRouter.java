package com.orderizer.data.get.search.orders.router;

import com.orderizer.data.get.search.orders.handler.GetOrderByGlobalIdentifierHandler;
import com.orderizer.data.get.search.orders.handler.GetOrderByLocalIdentifierHandler;
import com.orderizer.data.get.search.orders.handler.GetOrdersCountHandler;
import com.orderizer.data.get.search.orders.handler.GetOrdersSearchHandler;
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
    public RouterFunction<ServerResponse> dataGetRoute(Map<String, HandlerFunction<ServerResponse>> handlers) {
        return route().POST(path("/"), request -> {
                    if (request.queryParam("count").isPresent() && request.queryParam("count").get().equals("true")) {
                        return extract(handlers, GetOrdersCountHandler.class).handle(request);
                    } else {
                        return extract(handlers, GetOrdersSearchHandler.class).handle(request);
                    }
                }).nest(path("/"), builder -> builder.GET(path("/local"), extract(handlers, GetOrderByLocalIdentifierHandler.class))
                        .GET(path("/global"), extract(handlers, GetOrderByGlobalIdentifierHandler.class)))
                .build();
    }
}
