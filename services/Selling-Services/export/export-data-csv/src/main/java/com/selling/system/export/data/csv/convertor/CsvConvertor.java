package com.selling.system.export.data.csv.convertor;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.selling.system.export.data.csv.flatter.SalesFlatter;
import com.selling.system.export.data.csv.model.FlattedSale;
import com.selling.system.export.shared.convertor.DataConvertor;
import com.selling.system.shared.module.models.commands.ExportDataCommand;
import com.selling.system.shared.module.models.entities.Sale;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.StringWriter;


@Component
public class CsvConvertor implements DataConvertor {

    private final SalesFlatter salesFlatter;

    public CsvConvertor(SalesFlatter salesFlatter) {
        this.salesFlatter = salesFlatter;
    }

    @Override
    public Mono<byte[]> convert(Flux<Sale> salesFlux, ExportDataCommand exportDataCommand) {
        CsvMapper csvMapper = new CsvMapper();
        final CsvSchema schema = buildSchema();
        StringWriter writer = new StringWriter();
        return salesFlux.collectList()
                .flatMap(sales -> Mono.fromCallable(() -> salesFlatter.flat(sales)))
                .flatMap(flattedSales -> Mono.fromCallable(() -> {
                            csvMapper.writerFor(FlattedSale.class)
                                    .with(schema)
                                    .writeValues(writer)
                                    .writeAll(flattedSales);
                            return writer.toString();
                        })
                        .map(String::getBytes)
                        .onErrorMap(e -> new RuntimeException("Error processing JSON", e)));
    }

    private static CsvSchema buildSchema() {
        return CsvSchema.builder()
                .addColumn("id")
                .addColumn("saleDate")
                .addColumn("storeLocation")
                .addColumn("couponUsed")
                .addColumn("purchaseMethod")
                .addColumn("customer.gender")
                .addColumn("customer.age")
                .addColumn("customer.email")
                .addColumn("customer.satisfaction")
                .addColumn("item.name")
                .addColumn("item.tags")
                .addColumn("item.price")
                .addColumn("item.quantity")
                .setUseHeader(true)
                .build();
    }
}
