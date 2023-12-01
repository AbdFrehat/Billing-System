package com.selling.system.data.shared.module.service;

import com.selling.system.shared.module.models.commands.QueryCommand;
import com.selling.system.shared.module.models.responses.QueryResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface QueryResponseService {

    Mono<ResponseEntity<QueryResponse>> buildQueryResponse(QueryCommand queryCommand);
}
