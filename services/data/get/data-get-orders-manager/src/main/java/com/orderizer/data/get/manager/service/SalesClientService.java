package com.orderizer.data.get.manager.service;

import com.selling.system.shared.module.models.commands.DataCommand;
import com.selling.system.shared.module.models.responses.DataResponse;
import reactor.core.publisher.Mono;

/**
 * the main goal for this interface is to pass the query command based on the
 * query method to the right service.
 *
 * @author Abd Frehat
 * @since 1.0
 */
public interface SalesClientService {

    Mono<DataResponse> sendRequest(DataCommand dataCommand);

}
