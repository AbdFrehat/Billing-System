package com.selling.system.auth.shared.module.repository.api;

import com.selling.system.auth.shared.module.models.entities.User;
import com.selling.system.auth.shared.module.models.request.user.UserInsertRequest;
import com.selling.system.auth.shared.module.models.request.user.UserUpdateInfoRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsersRepository {

    Flux<User> retrieveAllUsers();
    Mono<User> retrieveUserByName(String username);
    Mono<Long> deleteUser(String username);
    Mono<Long> saveUser(UserInsertRequest request);
    Mono<Long> updateUserInfo(UserUpdateInfoRequest request);
    Mono<Long> updateUserPassword(String username, String password);
    Mono<Long> updateUserProfile(String username, String profileName);
    Mono<Long> enableUser(String username, boolean flag);
    Mono<Long> lockUser(String username, boolean flag);
    Mono<Long> expireCredentialUser(String username, boolean flag);
    Mono<Long> expireAccountUser(String username, boolean flag);
    Mono<Boolean> isEmailExists(String email);
    Mono<Boolean> isUsernameExists(String username);

}
