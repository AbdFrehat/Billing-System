package com.selling.system.auth.manager.model.response;

import lombok.*;

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
