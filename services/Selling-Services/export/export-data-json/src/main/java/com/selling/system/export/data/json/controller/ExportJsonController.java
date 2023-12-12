package com.selling.system.export.data.json.controller;

import com.selling.system.export.shared.client.DataManagerClient;
import com.selling.system.export.shared.convertor.DataConvertor;
import com.selling.system.export.shared.service.DataCommandBuilder;
import com.selling.system.shared.module.models.commands.ExportDataCommand;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Base64;

import static com.selling.system.shared.module.utils.CompressionUtil.compress;

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
    public Mono<ResponseEntity<byte[]>> exportToJson(@RequestBody ExportDataCommand command, @RequestParam("filename") String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);


        return dataConvertor.convert(dataManagerClient.retrieveExportedSales(dataCommandBuilder.build(command)))
                .flatMap(data -> Mono.fromCallable(() -> compress(data)))
                .map(data -> Base64.getEncoder().encode(data))
                .flatMap(data -> Mono.just(ResponseEntity.ok()
                        .headers(h -> h.addAll(headers))
                        .body(data))
                );
    }


}
