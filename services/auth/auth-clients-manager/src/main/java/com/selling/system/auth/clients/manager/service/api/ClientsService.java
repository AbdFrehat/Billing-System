package com.selling.system.auth.clients.manager.service.api;

import com.selling.system.auth.shared.module.models.dto.ClientDto;
import com.selling.system.auth.shared.module.models.dto.ClientsDto;
import com.selling.system.auth.shared.module.models.request.client.ClientDeleteRequest;
import com.selling.system.auth.shared.module.models.request.client.ClientInsertRequest;
import com.selling.system.auth.shared.module.models.request.client.ClientUpdateGrantTypeRequest;
import com.selling.system.auth.shared.module.models.request.client.ClientUpdateProfileRequest;
import com.selling.system.auth.shared.module.models.response.NameExistenceResponse;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import reactor.core.publisher.Mono;

public interface ClientsService {

    Mono<ClientsDto> retrieveAllClients();

    Mono<ClientDto> retrieveClientById(String clientId);

    Mono<UpdatedRowsResponse> updateClientProfile(ClientUpdateProfileRequest request);

    Mono<UpdatedRowsResponse> updateClientGrantType(ClientUpdateGrantTypeRequest request);

    Mono<UpdatedRowsResponse> deleteClient(ClientDeleteRequest request);

    Mono<UpdatedRowsResponse> createClient(ClientInsertRequest request);

    Mono<NameExistenceResponse> isClientExist(String clientName);
}
