package com.orderizer.core.api;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface Client<T, R> {

    Mono<R> call(T t, Map<String, Object> queryParams);
}
