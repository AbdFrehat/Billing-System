package com.selling.system.auth.shared.module.repository.api;

import com.selling.system.auth.shared.module.models.entities.User;
import com.selling.system.auth.shared.module.models.request.user.UserInsertRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsersRepository {

    Flux<User> retrieveAllUsers();

    Mono<User> retrieveUserByName(String username);

    Mono<Long> deleteUser(String username);

    Mono<Long> saveUser(UserInsertRequest request);
}
