package com.selling.system.shared.module.api;

import reactor.core.publisher.Mono;

public interface Mapper<T, R> {
    Mono<R> map(T t);
}
