package com.orderizer.core.wrapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WebResponseWrapper {

    protected <T> Mono<ResponseEntity<T>> response(Mono<T> body, HttpStatus status) {
        return body.map($ -> ResponseEntity.status(status).body($));
    }

    protected <T> Flux<ResponseEntity<T>> responses(Flux<T> body, HttpStatus status) {
        return body.map($ -> ResponseEntity.status(status).body($));
    }
}
