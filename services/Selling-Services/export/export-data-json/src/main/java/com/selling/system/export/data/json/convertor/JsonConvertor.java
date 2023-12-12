package com.selling.system.export.data.json.convertor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selling.system.export.shared.convertor.DataConvertor;
import com.selling.system.shared.module.models.entities.Sale;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class JsonConvertor implements DataConvertor {

    private final ObjectMapper objectMapper;

    public JsonConvertor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<byte[]> convert(Flux<Sale> salesFlux) {
        return salesFlux.collectList()
                .flatMap(sales -> Mono.fromCallable(() -> objectMapper.writeValueAsString(sales)))
                .map(String::getBytes)
                .onErrorMap(e -> new RuntimeException("Error processing JSON", e));
    }
}
