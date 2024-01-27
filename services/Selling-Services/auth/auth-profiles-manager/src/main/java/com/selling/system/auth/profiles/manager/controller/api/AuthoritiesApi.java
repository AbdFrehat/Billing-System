package com.selling.system.auth.profiles.manager.controller.api;

import com.selling.system.auth.shared.module.models.dto.AuthoritiesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@RequestMapping("/authorities")
public interface AuthoritiesApi {

    @GetMapping
    Mono<ResponseEntity<AuthoritiesDto>> getAuthorities();
}
