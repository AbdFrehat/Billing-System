package com.selling.system.auth.users.manager.controller.impl;

import com.selling.system.auth.shared.module.models.dto.UserDto;
import com.selling.system.auth.shared.module.models.dto.UsersDto;
import com.selling.system.auth.shared.module.models.request.user.UserDeleteRequest;
import com.selling.system.auth.shared.module.models.request.user.UserInsertRequest;
import com.selling.system.auth.shared.module.models.request.user.UserUpdateInfoRequest;
import com.selling.system.auth.shared.module.models.response.NameExistenceResponse;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import com.selling.system.auth.users.manager.controller.api.UsersApi;
import com.selling.system.auth.users.manager.service.api.UsersService;
import com.selling.system.shared.module.exceptions.client.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.selling.system.auth.shared.module.constants.Columns.User.EMAIL;
import static com.selling.system.auth.shared.module.constants.Columns.User.USERNAME;


@RestController
public class UsersController implements UsersApi {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public Mono<ResponseEntity<UsersDto>> getUsers() {
        return usersService.getAllUsers()
                .map($ -> ResponseEntity.status(HttpStatus.OK).body($));
    }

    @Override
    public Mono<ResponseEntity<UserDto>> getUser(String username) {
        return usersService.getUser(username)
                .map($ -> ResponseEntity.status(HttpStatus.OK).body($));
    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> deleteUser(UserDeleteRequest request) {
        return usersService.deleteUser(request)
                .map($ -> ResponseEntity.status(HttpStatus.ACCEPTED).body($));
    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> saveUser(UserInsertRequest request) {
        return usersService.saveUser(request)
                .map($ -> ResponseEntity.status(HttpStatus.CREATED).body($));
    }

    @Override
    public Mono<ResponseEntity<UpdatedRowsResponse>> updateUserInfo(UserUpdateInfoRequest request) {
        return usersService.updateUserInfo(request)
                .map($ -> ResponseEntity.status(HttpStatus.ACCEPTED).body($));
    }

    @Override
    public Mono<ResponseEntity<NameExistenceResponse>> isFieldExists(String fieldName, String value) {
        return usersService.isFieldValueExists(fieldName, value)
                .map($ -> ResponseEntity.status(HttpStatus.OK).body($));
    }
}
