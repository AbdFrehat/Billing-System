package com.selling.system.auth.clients.manager.service.impl;

import com.selling.system.auth.clients.manager.service.api.GrantTypesService;
import com.selling.system.auth.shared.module.models.dto.GrantTypesDto;
import com.selling.system.auth.shared.module.models.enums.GrantTypes;
import com.selling.system.auth.shared.module.models.request.grant.GrantTypeCreateRequest;
import com.selling.system.auth.shared.module.models.request.grant.GrantTypeDeleteRequest;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import com.selling.system.auth.shared.module.repository.api.GrantTypesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GrantTypesServiceImpl implements GrantTypesService {

    private final GrantTypesRepository grantTypesRepository;

    @Override
    public Mono<GrantTypesDto> retrieveGrantTypes() {
        return grantTypesRepository.retrieveGrantTypes()
                .map(grantType -> grantType.getGrantTypes().name())
                .collect(Collectors.toSet())
                .map(grantTypes -> GrantTypesDto.builder().grantTypes(grantTypes).build());
    }

    @Override
    public Mono<UpdatedRowsResponse> createGrantType(GrantTypeCreateRequest request) {
        return grantTypesRepository.createGrantType(GrantTypes.valueOf(request.getGrantTypes()))
                .map($ -> UpdatedRowsResponse.builder().count($).build());
    }

    @Override
    public Mono<UpdatedRowsResponse> deleteGrantType(GrantTypeDeleteRequest request) {
        return grantTypesRepository.deleteGrantType(request.getGrantTypes())
                .map($ -> UpdatedRowsResponse.builder().count($).build());
    }

}
