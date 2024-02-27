package com.selling.system.auth.manager.service.client.api;

import com.selling.system.auth.manager.model.client.response.UserResponse;
import reactor.core.publisher.Mono;

public interface UsersServiceClient {

    Mono<UserResponse> retrieveUserByUsername(String username);
}
