package com.selling.system.auth.clients.manager.controller.api;

import com.selling.system.auth.shared.module.models.dto.GrantTypesDto;
import com.selling.system.auth.shared.module.models.request.grant.GrantTypeCreateRequest;
import com.selling.system.auth.shared.module.models.request.grant.GrantTypeDeleteRequest;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/grant/types")
public interface GrantTypesApi {

    @GetMapping
    Mono<ResponseEntity<GrantTypesDto>> retrieveGrantTypes();

    @PostMapping
    Mono<ResponseEntity<UpdatedRowsResponse>> createGrantType(@RequestBody @Valid GrantTypeCreateRequest request);

    @DeleteMapping
    Mono<ResponseEntity<UpdatedRowsResponse>> deleteGrantType(@RequestBody @Valid GrantTypeDeleteRequest request);

}
