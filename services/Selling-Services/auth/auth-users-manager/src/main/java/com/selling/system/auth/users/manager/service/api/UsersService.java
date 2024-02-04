package com.selling.system.auth.users.manager.service.api;

import com.selling.system.auth.shared.module.models.dto.UserDto;
import com.selling.system.auth.shared.module.models.dto.UsersDto;
import com.selling.system.auth.shared.module.models.request.user.UserDeleteRequest;
import com.selling.system.auth.shared.module.models.request.user.UserInsertRequest;
import com.selling.system.auth.shared.module.models.request.user.UserUpdateInfoRequest;
import com.selling.system.auth.shared.module.models.response.NameExistenceResponse;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import reactor.core.publisher.Mono;

public interface UsersService {

    Mono<UsersDto> getAllUsers();

    Mono<UserDto> getUser(String username);

    Mono<UpdatedRowsResponse> deleteUser(UserDeleteRequest request);

    Mono<UpdatedRowsResponse> saveUser(UserInsertRequest request);

    Mono<UpdatedRowsResponse> updateUserInfo(UserUpdateInfoRequest request);

    Mono<NameExistenceResponse> isUsernameExists(String username);

    Mono<NameExistenceResponse> isEmailExists(String email);

    Mono<NameExistenceResponse> isFieldValueExists(String fieldName, String value);
}
