package com.selling.system.auth.profiles.manager.controller.api;

import com.selling.system.auth.shared.module.models.dto.ProfileDto;
import com.selling.system.auth.shared.module.models.dto.ProfilesDto;
import com.selling.system.auth.shared.module.models.request.profile.ProfileDeleteRequest;
import com.selling.system.auth.shared.module.models.request.profile.ProfileInsertRequest;
import com.selling.system.auth.shared.module.models.request.profile.ProfileUpdateRequest;
import com.selling.system.auth.shared.module.models.response.NameExistenceResponse;
import com.selling.system.auth.shared.module.models.response.UpdatedRowsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


public interface ProfilesApi {

    @GetMapping
    Mono<ResponseEntity<ProfilesDto>> getProfiles();

    @GetMapping("/{profileName}")
    Mono<ResponseEntity<ProfileDto>> getProfile(@PathVariable("profileName") String profileName);

    @DeleteMapping
    Mono<ResponseEntity<UpdatedRowsResponse>> deleteProfile(@RequestBody @Valid ProfileDeleteRequest request);

    @GetMapping("/exist/{profileName}")
    Mono<ResponseEntity<NameExistenceResponse>> isProfileExist(@PathVariable("profileName") String profileName);

    @PostMapping
    Mono<ResponseEntity<UpdatedRowsResponse>> saveProfile(@RequestBody @Valid ProfileInsertRequest profileInsertRequest);

    @PutMapping("/update")
    Mono<ResponseEntity<UpdatedRowsResponse>> updateProfile(@RequestBody @Valid ProfileUpdateRequest profileUpdateRequest);

}
