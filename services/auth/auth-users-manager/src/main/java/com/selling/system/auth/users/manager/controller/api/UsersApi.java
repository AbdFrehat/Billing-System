package com.selling.system.auth.users.manager.controller.api;

import com.selling.system.auth.shared.module.models.dto.UserDto;
import com.selling.system.auth.shared.module.models.dto.UsersDto;
import com.selling.system.auth.shared.module.models.request.user.*;
import com.selling.system.auth.shared.module.models.response.NameExistenceResponse;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

public interface UsersApi {

    @GetMapping
    Mono<ResponseEntity<UsersDto>> getUsers();

    @GetMapping("/{username}")
    Mono<ResponseEntity<UserDto>> getUser(@PathVariable("username") String username);

    @DeleteMapping
    Mono<ResponseEntity<UpdatedRowsResponse>> deleteUser(@RequestBody @Valid UserDeleteRequest request);

    @PostMapping
    Mono<ResponseEntity<UpdatedRowsResponse>> saveUser(@RequestBody @Valid UserInsertRequest request);

    @PutMapping("/info")
    Mono<ResponseEntity<UpdatedRowsResponse>> updateUserInfo(@RequestBody @Valid UserUpdateInfoRequest request);

    @PatchMapping("/password")
    Mono<ResponseEntity<UpdatedRowsResponse>> updateUserPassword(@RequestBody @Valid UserUpdatePasswordRequest request);

    @PatchMapping("/profile")
    Mono<ResponseEntity<UpdatedRowsResponse>> updateUserProfile(@RequestBody @Valid UserAssignProfileRequest request);

    @PatchMapping("/flag")
    Mono<ResponseEntity<UpdatedRowsResponse>> updateUserFlags(@RequestBody @Valid UserUpdateFlagsRequest request);

    @GetMapping("/exist")
    Mono<ResponseEntity<NameExistenceResponse>> isFieldExists(@RequestParam("fieldName") String fieldName, @RequestParam("value") String value);
}
