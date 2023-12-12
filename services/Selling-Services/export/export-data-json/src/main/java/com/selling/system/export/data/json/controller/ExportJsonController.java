package com.selling.system.export.data.json.controller;

import com.selling.system.export.shared.convertor.DataConvertor;
import com.selling.system.export.shared.export.DataExporter;
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



    private final DataConvertor dataConvertor;

    private final DataExporter dataExporter;

    public ExportJsonController(DataConvertor dataConvertor, DataExporter dataExporter) {
        this.dataConvertor = dataConvertor;
        this.dataExporter = dataExporter;
    }

    @GetMapping
    public Mono<ResponseEntity<byte[]>> exportToJson(@RequestBody ExportDataCommand command, @RequestParam("filename") String filename) {
        return dataExporter.export(dataConvertor, filename, command);
    }

}
