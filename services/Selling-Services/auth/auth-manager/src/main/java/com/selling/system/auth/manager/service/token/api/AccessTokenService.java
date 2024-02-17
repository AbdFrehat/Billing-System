package com.selling.system.auth.manager.service.token.api;

public interface AccessTokenService<T> {

    String buildToken(T t);
}
