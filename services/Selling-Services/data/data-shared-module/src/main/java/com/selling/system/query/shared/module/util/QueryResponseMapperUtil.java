package com.selling.system.query.shared.module.util;

import com.selling.system.shared.module.models.responses.QueryResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class QueryResponseMapperUtil {

    public static <T> Mono<ResponseEntity<QueryResponse>> mapFluxToResponse(Flux<T> data) {
        return data
                .collectList()
                .map(o -> QueryResponse.builder().data(o).build())
                .map(o -> ResponseEntity.ok().body(o));
    }

    public static <T> Mono<ResponseEntity<QueryResponse>> mapMonoToResponse(Mono<T> data) {
        return data
                .map(o -> QueryResponse.builder().data(o).build())
                .map(o -> ResponseEntity.ok().body(o));
    }
}
