package com.selling.system.data.shared.module.util;

import com.selling.system.shared.module.models.responses.DataResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class QueryResponseMapperUtil {

    public static <T> Mono<ResponseEntity<DataResponse>> mapFluxToResponse(Flux<T> data) {
        return data
                .collectList()
                .map(o -> DataResponse.builder().data(o).build())
                .map(o -> ResponseEntity.ok().body(o));
    }

    public static <T> Mono<ResponseEntity<DataResponse>> mapMonoToResponse(Mono<T> data) {
        return data
                .map(o -> DataResponse.builder().data(o).build())
                .map(o -> ResponseEntity.ok().body(o));
    }
}
