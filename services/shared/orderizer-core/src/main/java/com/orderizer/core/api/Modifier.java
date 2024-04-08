package com.orderizer.core.api;

import reactor.core.publisher.Mono;

public interface Modifier<T, R> {

    Mono<R> modify(T t, R r);
}
