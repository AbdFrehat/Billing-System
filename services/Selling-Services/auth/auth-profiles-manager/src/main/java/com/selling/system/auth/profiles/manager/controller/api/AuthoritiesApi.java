package com.selling.system.auth.profiles.manager.controller.api;

import com.selling.system.auth.shared.module.models.dto.AuthoritiesDto;
import com.selling.system.auth.shared.module.models.request.authority.AuthorityInsertRequest;
import com.selling.system.auth.shared.module.models.request.authority.AuthorityUpdateNameRequest;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/authorities")
public interface AuthoritiesApi {

    @GetMapping
    Mono<ResponseEntity<AuthoritiesDto>> getAuthorities();

    @PutMapping
    Mono<ResponseEntity<UpdatedRowsResponse>> updateAuthorityName(@RequestBody @Valid AuthorityUpdateNameRequest request);

    @PostMapping
    Mono<ResponseEntity<UpdatedRowsResponse>> saveAuthorities(@RequestBody @Valid AuthorityInsertRequest request);
}
