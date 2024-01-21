package com.selling.system.auth.profiles.manager.controller.api;

import com.selling.system.auth.shared.module.models.dto.ProfileDto;
import com.selling.system.auth.shared.module.models.dto.ProfilesDto;
import com.selling.system.auth.shared.module.models.request.ProfileRequestInsert;
import com.selling.system.auth.shared.module.models.request.ProfileUpdateRequest;
import com.selling.system.auth.shared.module.models.response.ProfileNameExistenceResponse;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


public interface ProfilesApi {

    @GetMapping
    Mono<ResponseEntity<ProfilesDto>> getProfiles();

    @GetMapping("/{profileName}")
    Mono<ResponseEntity<ProfileDto>> getProfile(@PathVariable("profileName") String profileName);

    @DeleteMapping("/{profileName}")
    Mono<ResponseEntity<UpdatedRowsResponse>> deleteProfile(@PathVariable("profileName") String profileName);

    @GetMapping("/exist/{profileName}")
    Mono<ResponseEntity<ProfileNameExistenceResponse>> isProfileExist(@PathVariable("profileName") String profileName);

    @PostMapping
    Mono<ResponseEntity<UpdatedRowsResponse>> saveProfile(@RequestBody ProfileRequestInsert profileRequestInsert);

    @PutMapping("/update")
    Mono<ResponseEntity<ProfileDto>> updateProfile(@RequestBody ProfileUpdateRequest profileUpdateRequest);

}
