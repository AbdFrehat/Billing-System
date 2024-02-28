package com.selling.system.auth.shared.module.wrapper;

import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import reactor.core.publisher.Mono;

public interface UpdateRowsResponseWrapper {

    default Mono<UpdatedRowsResponse> mapToUpdateRowsResponse(Mono<Long> count) {
        return count.map($ -> UpdatedRowsResponse.builder().count($).build());
    }
}
