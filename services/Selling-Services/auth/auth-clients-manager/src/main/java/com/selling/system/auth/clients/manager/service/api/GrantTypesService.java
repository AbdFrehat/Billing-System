package com.selling.system.auth.clients.manager.service.api;

import com.selling.system.auth.shared.module.models.dto.GrantTypesDto;
import com.selling.system.auth.shared.module.models.request.grant.GrantTypeCreateRequest;
import com.selling.system.auth.shared.module.models.request.grant.GrantTypeDeleteRequest;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import reactor.core.publisher.Mono;

public interface GrantTypesService {

    Mono<GrantTypesDto> retrieveGrantTypes();

    Mono<UpdatedRowsResponse> createGrantType(GrantTypeCreateRequest request);

    Mono<UpdatedRowsResponse> deleteGrantType(GrantTypeDeleteRequest request);
}
