package com.orderizer.data.update.orders.manager.validator.api;

import reactor.core.publisher.Mono;

public interface Validator<T> {

    Mono<T> validate(T t);
}
