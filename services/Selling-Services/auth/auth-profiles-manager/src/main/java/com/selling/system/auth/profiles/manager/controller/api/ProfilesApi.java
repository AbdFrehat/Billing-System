package com.selling.system.auth.profiles.manager.controller.api;

import com.selling.system.auth.shared.module.models.dto.ProfileDto;
import com.selling.system.auth.shared.module.models.dto.ProfilesDto;
import com.selling.system.auth.shared.module.models.request.ProfileUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProfilesApi {

    @GetMapping
    Mono<ResponseEntity<ProfilesDto>>  getProfiles();

//    @PutMapping("/update")
//    Mono<ResponseEntity<ProfileDto>> updateProfile(@RequestBody ProfileUpdateRequest profileUpdateRequest);

}
