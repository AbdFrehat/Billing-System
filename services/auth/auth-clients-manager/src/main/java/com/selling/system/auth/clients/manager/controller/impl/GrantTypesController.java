package com.selling.system.auth.clients.manager.controller.impl;

import com.selling.system.auth.clients.manager.controller.api.GrantTypesApi;
import com.selling.system.auth.clients.manager.service.api.GrantTypesService;
import com.selling.system.auth.shared.module.models.dto.GrantTypesDto;
import com.selling.system.auth.shared.module.models.request.grant.GrantTypeCreateRequest;
import com.selling.system.auth.shared.module.models.request.grant.GrantTypeDeleteRequest;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import com.orderizer.core.wrapper.WebResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class GrantTypesController extends WebResponseWrapper implements GrantTypesApi {

    private final GrantTypesService grantTypesService;

    @Override
    public Mono<ResponseEntity<GrantTypesDto>> retrieveGrantTypes() {
        return response(grantTypesService.retrieveGrantTypes(), HttpStatus.OK);

    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> createGrantType(GrantTypeCreateRequest request) {
        return response(grantTypesService.createGrantType(request), HttpStatus.CREATED);
    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> deleteGrantType(GrantTypeDeleteRequest request) {
        return response(grantTypesService.deleteGrantType(request), HttpStatus.ACCEPTED);
    }
}
