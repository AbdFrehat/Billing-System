package com.selling.system.auth.profiles.manager.controller.impl;

import com.selling.system.auth.profiles.manager.controller.api.AuthoritiesApi;
import com.selling.system.auth.profiles.manager.service.api.AuthoritiesService;
import com.selling.system.auth.shared.module.models.dto.AuthoritiesDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthoritiesController implements AuthoritiesApi {

    private final AuthoritiesService authoritiesService;

    public AuthoritiesController(AuthoritiesService authoritiesService) {
        this.authoritiesService = authoritiesService;
    }

    @Override
    public Mono<ResponseEntity<AuthoritiesDto>> getAuthorities() {
        return authoritiesService.getAuthorities()
                .map($ -> ResponseEntity.status(HttpStatus.OK).body($));
    }
}
