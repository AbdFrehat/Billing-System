package com.selling.system.export.data.manager.config;

import com.selling.system.shared.module.exceptions.Technical.BufferSizeInvalidException;
import com.selling.system.shared.module.exceptions.Technical.SizeInvalidException;
import com.selling.system.shared.module.utils.SizeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientInit {

    @Value("${config.web-client.buffer}")
    private String webClientBuffer;

    @Bean
    
    WebClient.Builder webClientBuilder() {
        try {
            int bufferSize = SizeUtil.parseSize(webClientBuffer);
            return WebClient.builder()
                    .exchangeStrategies(ExchangeStrategies
                            .builder()
                            .codecs(codecs -> codecs.defaultCodecs()
                                    .maxInMemorySize(bufferSize))
                            .build());
        } catch (SizeInvalidException e) {
            throw new BufferSizeInvalidException("invalid config.web-client.buffer: " + e.getMessage());
        }

    }
}
