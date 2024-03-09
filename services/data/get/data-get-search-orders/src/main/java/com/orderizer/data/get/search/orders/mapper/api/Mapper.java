package com.orderizer.data.get.search.orders.mapper.api;

import reactor.core.publisher.Mono;

public interface Mapper<T, R> {
    Mono<R> map(T t);
}
