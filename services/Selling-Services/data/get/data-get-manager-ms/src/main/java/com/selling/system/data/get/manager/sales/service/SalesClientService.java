package com.selling.system.data.get.manager.sales.service;

import com.selling.system.shared.module.models.commands.DataCommand;
import com.selling.system.shared.module.models.responses.QueryResponse;
import reactor.core.publisher.Mono;

/**
 * the main goal for this interface is to pass the query command based on the
 * query method to the right service.
 *
 * @author Abd Frehat
 * @since 1.0
 */
public interface SalesClientService {

    Mono<QueryResponse> sendRequest(DataCommand dataCommand);

}
