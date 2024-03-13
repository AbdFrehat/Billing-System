package com.orderizer.data.save.orders.mapper.api;

import reactor.core.publisher.Mono;

public interface Mapper<T, R> {
    Mono<R> map(T t);
}
