package com.selling.system.export.data.manager.controller;

import com.selling.system.export.data.manager.client.ExportServicesClient;
import com.selling.system.shared.module.models.commands.ExportDataCommand;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ExportManagerController {


    private final ExportServicesClient exportServicesClient;

    public ExportManagerController(ExportServicesClient exportServicesClient) {
        this.exportServicesClient = exportServicesClient;
    }

    @PostMapping
    public Mono<ResponseEntity<byte[]>> export(@RequestBody @Valid ExportDataCommand exportDataCommand) {
        return exportServicesClient.sendCommand(exportDataCommand).map(ResponseEntity::ok);
    }
}
