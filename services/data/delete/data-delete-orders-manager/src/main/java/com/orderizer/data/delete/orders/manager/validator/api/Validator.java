package com.orderizer.data.delete.orders.manager.validator.api;

import reactor.core.publisher.Mono;

public interface Validator<T> {

    Mono<T> validate(T t);
}
