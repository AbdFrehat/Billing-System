package com.selling.system.export.data.manager.client;

import com.selling.system.export.data.manager.config.ServicesContextPathConfig;
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

@Service
public class ExportServicesClient {

    private final WebClient webClient;

    private final ServicesContextPathConfig servicesContextPathConfig;
    public ExportServicesClient(WebClient.Builder webClientBuilder, ServicesContextPathConfig servicesContextPathConfig) {
        this.webClient = webClientBuilder.build();
        this.servicesContextPathConfig = servicesContextPathConfig;
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
            case XLSX -> servicesContextPathConfig.getExportDataXlsx();
            case PDF -> servicesContextPathConfig.getExportDataPdf();
            case JSON -> servicesContextPathConfig.getExportDataJson();
            case CSV -> servicesContextPathConfig.getExportDataCsv();
            case XML -> servicesContextPathConfig.getExportDataXml();
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
