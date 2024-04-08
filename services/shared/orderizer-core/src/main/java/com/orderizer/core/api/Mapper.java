package com.orderizer.core.api;

import reactor.core.publisher.Mono;

public interface Mapper<T, R> {
    Mono<R> map(T t);
}
