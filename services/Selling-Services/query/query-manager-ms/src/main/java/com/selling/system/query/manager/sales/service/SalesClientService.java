package com.selling.system.query.manager.sales.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.selling.system.query.manager.sales.models.entites.Sale;
import com.selling.system.shared.module.models.commands.QueryCommand;
import com.selling.system.shared.module.models.responses.QueryResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SalesClientService {

    Mono<QueryResponse> sendRequest(QueryCommand queryCommand);

}
