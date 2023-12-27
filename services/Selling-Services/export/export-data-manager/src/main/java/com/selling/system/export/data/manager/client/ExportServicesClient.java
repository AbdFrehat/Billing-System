package com.selling.system.export.data.manager.client;

import com.selling.system.shared.module.handlers.ClientExceptionHandler;
import com.selling.system.shared.module.models.commands.ExportDataCommand;
import com.selling.system.shared.module.models.enums.ExportType;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;

@Service
public class ExportServicesClient {

    private final WebClient webClient;

    private final Map<String, String> servicesContextPath;

    public ExportServicesClient(WebClient.Builder webClientBuilder, Map<String, String> servicesContextPath) {
        this.webClient = webClientBuilder.build();
        this.servicesContextPath = servicesContextPath;
    }

    public Mono<byte[]> sendCommand(ExportDataCommand exportDataCommand) {
        return this.webClient
                .post()
                .uri(buildUri(exportDataCommand))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(exportDataCommand.getExportDataFilter())
                .retrieve()
                .onStatus(HttpStatusCode::isError, new ClientExceptionHandler(exportDataCommand.getExportType().name()))
                .bodyToMono(byte[].class);
    }

    private String getUri(ExportType exportType) {
        return switch (exportType) {
            case XLSX -> servicesContextPath.get("export-data-xlsx");
            case PDF -> servicesContextPath.get("export-data-pdf");
            case JSON -> servicesContextPath.get("export-data-json");
            case CSV -> servicesContextPath.get("export-data-csv");
            case XML -> servicesContextPath.get("export-data-xml");
        };
    }

    private String buildUri(ExportDataCommand exportDataCommand) {
        return UriComponentsBuilder
                .fromUri(URI.create(getUri(exportDataCommand.getExportType())))
                .queryParam("filename", exportDataCommand.getFileName())
                .build()
                .toUriString();
    }
}
