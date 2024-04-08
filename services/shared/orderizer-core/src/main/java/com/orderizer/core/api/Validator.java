package com.orderizer.core.api;

import reactor.core.publisher.Mono;

public interface Validator<T> {

    Mono<T> validate(T t);
}
