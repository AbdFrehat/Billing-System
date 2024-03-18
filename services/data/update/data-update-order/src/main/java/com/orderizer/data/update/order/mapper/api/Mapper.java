package com.orderizer.data.update.order.mapper.api;

import reactor.core.publisher.Mono;

public interface Mapper<T, R> {
    Mono<R> map(T t);
}
