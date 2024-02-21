package com.selling.system.auth.manager.model.response.pkce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PKCEGrantTypeResponse {

    private String accessToken;

    private String refreshToken;

    private String idToken;

    private LocalDateTime issuedAt;

    private Date expiredAt;
}
