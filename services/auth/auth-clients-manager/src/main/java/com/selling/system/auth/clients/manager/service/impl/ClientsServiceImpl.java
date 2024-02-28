package com.selling.system.auth.clients.manager.service.impl;

import com.selling.system.auth.clients.manager.service.api.ClientsService;
import com.selling.system.auth.shared.module.mapper.api.Mapper;
import com.selling.system.auth.shared.module.models.dto.ClientDto;
import com.selling.system.auth.shared.module.models.dto.ClientsDto;
import com.selling.system.auth.shared.module.models.enums.GrantTypes;
import com.selling.system.auth.shared.module.models.request.client.ClientDeleteRequest;
import com.selling.system.auth.shared.module.models.request.client.ClientInsertRequest;
import com.selling.system.auth.shared.module.models.request.client.ClientUpdateGrantTypeRequest;
import com.selling.system.auth.shared.module.models.request.client.ClientUpdateProfileRequest;
import com.selling.system.auth.shared.module.models.response.NameExistenceResponse;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import com.selling.system.auth.shared.module.repository.api.ClientsRepository;
import com.selling.system.auth.shared.module.util.ClientsUtil;
import com.selling.system.auth.shared.module.util.NameExistenceResponseWrapper;
import com.selling.system.auth.shared.module.wrapper.UpdateRowsResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ClientsServiceImpl implements ClientsService, UpdateRowsResponseWrapper, NameExistenceResponseWrapper {

    private final ClientsRepository clientsRepository;

    private final Mapper mapper;

    @Override
    public Mono<ClientsDto> retrieveAllClients() {
        return clientsRepository.retrieveAllClients()
                .map(mapper::clientToClientDto)
                .collectList()
                .map(mapper::clientsToClientsDto);
    }

    @Override
    public Mono<ClientDto> retrieveClientById(String clientId) {
        return clientsRepository.retrieveClientById(clientId)
                .map(mapper::clientToClientDto);
    }

    @Override
    public Mono<UpdatedRowsResponse> updateClientProfile(ClientUpdateProfileRequest request) {
        return mapToUpdateRowsResponse(clientsRepository.updateClientProfile(request.getClientId(), request.getProfileName()));
    }

    @Override
    public Mono<UpdatedRowsResponse> updateClientGrantType(ClientUpdateGrantTypeRequest request) {
        return mapToUpdateRowsResponse(clientsRepository.updateClientGrantType(request.getClientId(), GrantTypes.valueOf(request.getGrantType())));
    }

    @Override
    public Mono<UpdatedRowsResponse> deleteClient(ClientDeleteRequest request) {
        return mapToUpdateRowsResponse(clientsRepository.deleteClient(request.getClientId()));
    }

    @Override
    public Mono<UpdatedRowsResponse> createClient(ClientInsertRequest request) {
        return mapToUpdateRowsResponse(clientsRepository.createClient(ClientsUtil.generateClientId(),
                request.getClientName(),
                ClientsUtil.generateClientSecret(),
                request.getProfileName(),
                GrantTypes.valueOf(request.getGrantType())));
    }

    @Override
    public Mono<NameExistenceResponse> isClientExist(String clientName) {
        return mapToNameExistenceResponse(clientsRepository.isClientExists(clientName));
    }
}
