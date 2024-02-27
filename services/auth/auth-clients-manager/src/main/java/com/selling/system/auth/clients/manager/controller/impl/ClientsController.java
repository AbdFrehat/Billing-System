package com.selling.system.auth.clients.manager.controller.impl;

import com.selling.system.auth.clients.manager.controller.api.ClientsApi;
import com.selling.system.auth.clients.manager.service.api.ClientsService;
import com.selling.system.auth.shared.module.models.dto.ClientDto;
import com.selling.system.auth.shared.module.models.dto.ClientsDto;
import com.selling.system.auth.shared.module.models.request.client.ClientDeleteRequest;
import com.selling.system.auth.shared.module.models.request.client.ClientInsertRequest;
import com.selling.system.auth.shared.module.models.request.client.ClientUpdateGrantTypeRequest;
import com.selling.system.auth.shared.module.models.request.client.ClientUpdateProfileRequest;
import com.selling.system.auth.shared.module.models.response.NameExistenceResponse;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import com.selling.system.shared.module.wrapper.WebResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ClientsController extends WebResponseWrapper implements ClientsApi {

    private final ClientsService clientsService;

    @Override
    public Mono<ResponseEntity<ClientsDto>> retrieveAllClients() {
        return response(clientsService.retrieveAllClients(), HttpStatus.OK);
    }

    @Override
    public Mono<ResponseEntity<ClientDto>> retrieveClientById(String clientId) {
        return response(clientsService.retrieveClientById(clientId), HttpStatus.OK);
    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> updateClientProfile(ClientUpdateProfileRequest request) {
        return response(clientsService.updateClientProfile(request), HttpStatus.ACCEPTED);
    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> updateClientGrantType(ClientUpdateGrantTypeRequest request) {
        return response(clientsService.updateClientGrantType(request), HttpStatus.ACCEPTED);
    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> deleteClient(ClientDeleteRequest request) {
        return response(clientsService.deleteClient(request), HttpStatus.ACCEPTED);
    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> createClient(ClientInsertRequest request) {
        return response(clientsService.createClient(request), HttpStatus.ACCEPTED);
    }

    @Override
    public Mono<ResponseEntity<NameExistenceResponse>> isClientExist(String clientName) {
        return response(clientsService.isClientExist(clientName), HttpStatus.OK);
    }
}
