package com.orderizer.data.save.orders.manager.validator.api;

import reactor.core.publisher.Mono;

public interface Validator<T> {

    Mono<T> validate(T t);
}
