package com.selling.system.shared.module.api;

import reactor.core.publisher.Mono;

public interface Modifier<T, R> {

    Mono<R> modify(T t, R r);
}
