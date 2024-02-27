package com.selling.system.shared.module.filters.web;

import com.selling.system.shared.module.config.CommonHeaders;
import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HeaderLocaleFilter implements WebFilter {

    private final CommonHeaders commonHeaders;

    @Override
    public @NonNull Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        List<String> acceptLang = exchange.getRequest().getHeaders().get("Accept-Language");
        String languageValue = acceptLang != null && !acceptLang.isEmpty() ? acceptLang.get(0) : "en";
        commonHeaders.setLang(languageValue);
        return chain.filter(exchange);
    }
}
