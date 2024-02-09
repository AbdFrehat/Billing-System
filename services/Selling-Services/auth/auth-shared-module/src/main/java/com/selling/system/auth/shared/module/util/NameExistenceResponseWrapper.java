package com.selling.system.auth.shared.module.util;

import com.selling.system.auth.shared.module.models.response.NameExistenceResponse;
import reactor.core.publisher.Mono;

public interface NameExistenceResponseWrapper {
    default Mono<NameExistenceResponse> mapToNameExistenceResponse(Mono<Boolean> exist) {
        return exist.map($ -> NameExistenceResponse.builder().exists($).build());
    }
}
