package com.selling.system.auth.manager.controller.api;

import com.selling.system.auth.manager.model.request.IssueClientTokenRequest;
import com.selling.system.auth.manager.model.response.ClientGrantTypeResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@RequestMapping("/auth")
@Tag(name = "Authentication Controller", description = "Operations related to authentication")
public interface AuthenticationApi {

    @PostMapping("/client")
    @ApiResponse(description = "Contains the tokens needed to authenticate.")
    Mono<ResponseEntity<ClientGrantTypeResponse>> authenticate(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Contains the secrets to authenticate base don them")
                                                               @RequestBody @Valid IssueClientTokenRequest request);
}
