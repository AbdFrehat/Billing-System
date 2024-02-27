package com.selling.system.auth.profiles.manager.service.api;

import com.selling.system.auth.shared.module.models.dto.AuthoritiesDto;
import com.selling.system.auth.shared.module.models.request.authority.AuthorityDeleteRequest;
import com.selling.system.auth.shared.module.models.request.authority.AuthorityInsertRequest;
import com.selling.system.auth.shared.module.models.request.authority.AuthorityUpdateNameRequest;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import reactor.core.publisher.Mono;

public interface AuthoritiesService {

    Mono<AuthoritiesDto> getAuthorities();

    Mono<UpdatedRowsResponse> updateAuthorityName(AuthorityUpdateNameRequest request);

    Mono<UpdatedRowsResponse> saveAuthorities(AuthorityInsertRequest request);

    Mono<UpdatedRowsResponse> deleteAuthority(AuthorityDeleteRequest request);
}
