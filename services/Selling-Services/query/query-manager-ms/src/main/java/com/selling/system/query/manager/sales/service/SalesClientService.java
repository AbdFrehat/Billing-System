package com.selling.system.query.manager.sales.service;

import com.selling.system.shared.module.models.commands.QueryCommand;
import com.selling.system.shared.module.models.responses.QueryResponse;
import reactor.core.publisher.Mono;

public interface SalesClientService {

    Mono<QueryResponse> sendRequest(QueryCommand queryCommand);

}
