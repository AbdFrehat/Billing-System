package com.selling.system.shared.module.utils;

import com.selling.system.shared.module.exceptions.Technical.QueryParamsMissingException;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class QueryParamsUtil {

    private QueryParamsUtil() {

    }

    public static <T> Mono<T> getQueryParam(ServerRequest serverRequest, String queryName, Function<String, T> mapper) {
        return Mono.justOrEmpty(serverRequest.queryParam(queryName))
                .switchIfEmpty(Mono.error(new QueryParamsMissingException("The query param " + queryName + " is missing")))
                .map(mapper);
    }

}