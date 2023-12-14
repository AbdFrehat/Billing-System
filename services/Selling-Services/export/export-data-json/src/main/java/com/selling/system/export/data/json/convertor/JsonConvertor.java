package com.selling.system.export.data.json.convertor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selling.system.export.shared.convertor.DataConvertor;
import com.selling.system.shared.module.models.commands.ExportDataCommand;
import com.selling.system.shared.module.models.entities.Sale;
import com.selling.system.shared.module.models.wrappers.Sales;
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
    public Mono<byte[]> convert(Flux<Sale> salesFlux, ExportDataCommand exportDataCommand) {
        return salesFlux.collectList()
                .flatMap(sales -> Mono.fromCallable(() -> objectMapper.writeValueAsString(new Sales(sales))))
                .map(String::getBytes)
                .onErrorMap(e -> new RuntimeException("Error processing JSON", e));
    }
}
