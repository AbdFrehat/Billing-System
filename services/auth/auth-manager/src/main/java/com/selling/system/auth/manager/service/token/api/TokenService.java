package com.selling.system.auth.manager.service.token.api;

import java.util.Date;

public interface TokenService<T> {

    String buildToken(T t, Date expirationDateTime);
}
