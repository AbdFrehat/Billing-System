package com.selling.system.shared.module.filters.web;

import io.micrometer.common.lang.NonNull;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Locale;

import static com.selling.system.shared.module.utils.StringUtil.isNotEmpty;

@Component
public class HeaderLocaleFilter implements WebFilter {
    @Override
    public @NonNull Mono<Void> filter(@NonNull ServerWebExchange exchange, WebFilterChain chain) {
        List<String> acceptLang = exchange.getRequest().getHeaders().get("Accept-Language");
        String languageValue = acceptLang != null && !acceptLang.isEmpty() ? acceptLang.get(0) : "";
        if (isNotEmpty(languageValue)) {
            LocaleContextHolder.setLocaleContext(() ->
                    Locale.forLanguageTag(languageValue));
        } else {
            LocaleContext localeContext = exchange.getLocaleContext();
            LocaleContextHolder.setLocaleContext(localeContext);
        }
        return chain.filter(exchange);
    }
}
