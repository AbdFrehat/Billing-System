package com.selling.system.auth.profiles.manager.service.impl;

import com.selling.system.auth.profiles.manager.service.api.AuthoritiesService;
import com.selling.system.auth.shared.module.mapper.api.Mapper;
import com.selling.system.auth.shared.module.models.dto.AuthoritiesDto;
import com.selling.system.auth.shared.module.models.request.authority.AuthorityInsertRequest;
import com.selling.system.auth.shared.module.models.request.authority.AuthorityUpdateNameRequest;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import com.selling.system.auth.shared.module.repository.api.AuthoritiesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

    private final AuthoritiesRepository authoritiesRepository;

    private final Mapper mapper;

    public AuthoritiesServiceImpl(AuthoritiesRepository authoritiesRepository, Mapper mapper) {
        this.authoritiesRepository = authoritiesRepository;
        this.mapper = mapper;
    }

    @Override
    public Mono<AuthoritiesDto> getAuthorities() {
        return authoritiesRepository.retrieveAllAuthorities()
                .collect(Collectors.toSet())
                .map(mapper::authoritiesToAuthoritiesDto)
                .map($ -> AuthoritiesDto.builder().authorities($).build());
    }

    @Override
    public Mono<UpdatedRowsResponse> updateAuthorityName(AuthorityUpdateNameRequest request) {
        return authoritiesRepository.updateAuthorityName(request.getAuthorityName(),
                        request.getUpdatedAuthorityName())
                .map($ -> UpdatedRowsResponse.builder().count($).build());
    }

    @Override
    public Mono<UpdatedRowsResponse> saveAuthorities(AuthorityInsertRequest request) {
        return authoritiesRepository.saveAuthorities(request.getAuthorities())
                .map($ -> UpdatedRowsResponse.builder().count($).build());
    }
}
