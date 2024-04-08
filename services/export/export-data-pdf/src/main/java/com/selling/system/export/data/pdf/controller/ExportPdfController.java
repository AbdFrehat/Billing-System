package com.selling.system.export.data.pdf.controller;

import com.selling.system.export.shared.convertor.DataConvertor;
import com.selling.system.export.shared.export.DataExporter;
import com.orderizer.core.models.commands.ExportDataFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ExportPdfController {

    private final DataConvertor dataConvertor;

    private final DataExporter dataExporter;

    public ExportPdfController(DataConvertor dataConvertor, DataExporter dataExporter) {
        this.dataConvertor = dataConvertor;
        this.dataExporter = dataExporter;
    }

    @PostMapping
    public Mono<ResponseEntity<byte[]>> exportToPdf(@RequestBody ExportDataFilter command, @RequestParam("filename") String filename) {
        return dataExporter.export(dataConvertor, filename, command);
    }

}
