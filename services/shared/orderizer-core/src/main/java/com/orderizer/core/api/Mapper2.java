package com.orderizer.core.api;

import reactor.core.publisher.Mono;

public interface Mapper2<T, R, K> {
    Mono<K> map(T t, R r);
}
