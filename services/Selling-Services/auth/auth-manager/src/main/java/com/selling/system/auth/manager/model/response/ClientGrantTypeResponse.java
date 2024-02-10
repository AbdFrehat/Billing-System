package com.selling.system.auth.manager.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientGrantTypeResponse {

    private String accessToken;

    private String refreshToken;

    private LocalDateTime issuedAt;

    private LocalDateTime expiredAt;

}
