package com.selling.system.export.shared.client;

import com.selling.system.shared.module.handlers.ClientExceptionHandler;
import com.selling.system.shared.module.models.commands.DataCommand;
import com.selling.system.shared.module.models.entities.Sale;
import com.selling.system.shared.module.models.responses.DataResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

import static com.selling.system.shared.module.convertors.ObjectToSalesConvertor.toSales;

@Component
public class DataManagerClient {

    private final WebClient webClient;

    @Value("${config.services.context-path.data-manager-ms}")
    private String dataManagerMsContextPath;

    public DataManagerClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Flux<Sale> retrieveExportedSales(DataCommand dataCommand) {
        return this.webClient
                .post()
                .uri(dataManagerMsContextPath)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dataCommand)
                .retrieve()
                .onStatus(HttpStatusCode::isError, new ClientExceptionHandler(dataCommand.getCommandType().name()))
                .bodyToMono(DataResponse.class)
                .flatMapMany(dataResponse -> {
                    if (dataResponse.getData() instanceof List<?> data) {
                        if (data.isEmpty()) return Flux.empty();
                    }
                    dataCommand.setPage(dataCommand.getPage() + 1);
                    return Flux.concat(Flux.fromIterable(toSales(dataResponse.getData())),
                            retrieveExportedSales(dataCommand));
                });
    }
}
