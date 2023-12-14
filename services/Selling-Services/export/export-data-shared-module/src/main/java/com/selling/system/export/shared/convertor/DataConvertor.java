package com.selling.system.export.shared.convertor;

import com.selling.system.shared.module.models.commands.ExportDataCommand;
import com.selling.system.shared.module.models.entities.Sale;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DataConvertor {

    Mono<byte[]> convert(Flux<Sale> sales, ExportDataCommand exportDataCommand);
}
