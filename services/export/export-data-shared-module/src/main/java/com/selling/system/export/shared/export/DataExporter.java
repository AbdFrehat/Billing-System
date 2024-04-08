package com.selling.system.export.shared.export;

import com.selling.system.export.shared.client.DataManagerClient;
import com.selling.system.export.shared.convertor.DataConvertor;
import com.selling.system.export.shared.service.DataCommandBuilder;
import com.orderizer.core.models.commands.ExportDataFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Base64;

@Component
public class DataExporter {

    private final DataManagerClient dataManagerClient;

    private final DataCommandBuilder dataCommandBuilder;

    public DataExporter(DataManagerClient dataManagerClient, DataCommandBuilder dataCommandBuilder) {
        this.dataManagerClient = dataManagerClient;
        this.dataCommandBuilder = dataCommandBuilder;
    }

    public Mono<ResponseEntity<byte[]>> export(DataConvertor dataConvertor, String filename, ExportDataFilter command) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        return dataConvertor.convert(dataManagerClient.retrieveExportedSales(dataCommandBuilder.build(command)), command)
                .map(data -> Base64.getEncoder().encode(data))
                .flatMap(data -> Mono.just(ResponseEntity.ok()
                        .headers(h -> h.addAll(headers))
                        .body(data))
                );
    }
}
