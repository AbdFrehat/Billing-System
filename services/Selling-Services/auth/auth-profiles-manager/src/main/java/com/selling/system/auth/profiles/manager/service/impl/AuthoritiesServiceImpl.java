package com.selling.system.auth.profiles.manager.service.impl;

import com.selling.system.auth.profiles.manager.service.api.AuthoritiesService;
import com.selling.system.auth.shared.module.mapper.api.Mapper;
import com.selling.system.auth.shared.module.models.dto.AuthoritiesDto;
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
                .map(authoritiesDto -> AuthoritiesDto.builder().authorities(authoritiesDto).build());
    }
}
