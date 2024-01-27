package com.selling.system.auth.profiles.manager.service.api;

import com.selling.system.auth.shared.module.models.dto.AuthoritiesDto;
import reactor.core.publisher.Mono;

public interface AuthoritiesService {

    Mono<AuthoritiesDto> getAuthorities();


}
