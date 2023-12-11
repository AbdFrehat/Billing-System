package com.selling.system.export.data.json.controller;

import com.selling.system.export.shared.client.DataManagerClient;
import com.selling.system.export.shared.convertor.DataConvertor;
import com.selling.system.export.shared.service.DataCommandBuilder;
import com.selling.system.shared.module.models.commands.ExportDataCommand;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ExportJsonController {

    private final DataManagerClient dataManagerClient;

    private final DataCommandBuilder dataCommandBuilder;

    private final DataConvertor dataConvertor;

    public ExportJsonController(DataManagerClient dataManagerClient, DataCommandBuilder dataCommandBuilder, DataConvertor dataConvertor) {
        this.dataManagerClient = dataManagerClient;
        this.dataCommandBuilder = dataCommandBuilder;
        this.dataConvertor = dataConvertor;
    }

    @GetMapping
    public Mono<ResponseEntity<ByteArrayResource>> exportToJson(@RequestBody ExportDataCommand command) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=orders.json");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        return dataConvertor.convert(dataManagerClient.retrieveExportedSales(dataCommandBuilder.build(command)))
                .map(ByteArrayResource::new)
                .flatMap(resource -> Mono.just(ResponseEntity.ok()
                        .headers(h -> h.addAll(headers))
                        .body(resource))
                );
    }
}
