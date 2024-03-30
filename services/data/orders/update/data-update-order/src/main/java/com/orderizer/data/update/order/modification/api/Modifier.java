package com.orderizer.data.update.order.modification.api;

import reactor.core.publisher.Mono;

public interface Modifier<T, R> {

    Mono<R> modify(T t, R r);
}
