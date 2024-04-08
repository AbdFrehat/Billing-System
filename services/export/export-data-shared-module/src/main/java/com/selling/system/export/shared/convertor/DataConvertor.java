package com.selling.system.export.shared.convertor;

import com.orderizer.core.models.commands.ExportDataFilter;
import com.orderizer.core.models.entities.Sale;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DataConvertor {

    Mono<byte[]> convert(Flux<Sale> sales, ExportDataFilter exportDataCommand);
}
