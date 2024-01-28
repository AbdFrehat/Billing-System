package com.selling.system.auth.profiles.manager.controller.impl;

import com.selling.system.auth.profiles.manager.controller.api.AuthoritiesApi;
import com.selling.system.auth.profiles.manager.service.api.AuthoritiesService;
import com.selling.system.auth.shared.module.models.dto.AuthoritiesDto;
import com.selling.system.auth.shared.module.models.request.authority.AuthorityDeleteRequest;
import com.selling.system.auth.shared.module.models.request.authority.AuthorityInsertRequest;
import com.selling.system.auth.shared.module.models.request.authority.AuthorityUpdateNameRequest;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
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

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> updateAuthorityName(AuthorityUpdateNameRequest request) {
        return authoritiesService.updateAuthorityName(request)
                .map($ -> ResponseEntity.status(HttpStatus.ACCEPTED).body($));
    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> saveAuthorities(AuthorityInsertRequest request) {
        return authoritiesService.saveAuthorities(request)
                .map($ -> ResponseEntity.status(HttpStatus.CREATED).body($));
    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> deleteAuthority(AuthorityDeleteRequest request) {
        return authoritiesService.deleteAuthority(request)
                .map($ -> ResponseEntity.status(HttpStatus.ACCEPTED).body($));
    }
}
