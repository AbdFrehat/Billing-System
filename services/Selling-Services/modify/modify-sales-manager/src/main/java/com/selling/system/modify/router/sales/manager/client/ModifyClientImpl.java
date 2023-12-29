package com.selling.system.modify.router.sales.manager.client;

import com.selling.system.shared.module.exceptions.business.CommandTypeNotSupportedException;
import com.selling.system.shared.module.models.entities.Sale;
import com.selling.system.shared.module.models.enums.CommandType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class ModifyClientImpl implements ModifyClient {

    private final WebClient webClient;

    private final Map<String, String> servicesContextPath;

    public ModifyClientImpl(WebClient.Builder webClientBuilder, Map<String, String> servicesContextPath) {
        this.webClient = webClientBuilder.build();
        this.servicesContextPath = servicesContextPath;
    }

    public Mono<ResponseEntity<Void>> sendModifyCommand(Sale sale, CommandType commandType) {
        return switch (commandType) {
            case SAVE_SALE -> this.sendSaveCommand(sale);
            case UPDATE_SALE -> this.sendUpdateCommand(sale);
            case DELETE_SALE -> this.sendDeleteCommand(sale);
            default -> throw new CommandTypeNotSupportedException("The requested command is not supported");
        };
    }

    public Mono<ResponseEntity<Void>> sendMultiModifyCommand(List<Sale> sales, CommandType commandType) {
        return switch (commandType) {
            case SAVE_SALES -> this.sendSaveCommands(sales);
            case UPDATE_SALES -> this.sendUpdateCommands(sales);
            case DELETE_SALES -> this.sendDeleteCommands(sales);
            default -> throw new CommandTypeNotSupportedException("The requested command is not supported");
        };
    }

    private Mono<ResponseEntity<Void>> sendUpdateCommand(Sale sale) {
        return this.webClient
                .post()
                .uri(servicesContextPath.get("modify-update-sales-ms"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(sale)
                .retrieve()
                .toBodilessEntity();
    }

    private Mono<ResponseEntity<Void>> sendUpdateCommands(List<Sale> sales) {
        return this.webClient
                .post()
                .uri(servicesContextPath.get("modify-update-sales-ms") + "multi")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(sales)
                .retrieve()
                .toBodilessEntity();
    }

    private Mono<ResponseEntity<Void>> sendSaveCommand(Sale sale) {
        return this.webClient
                .post()
                .uri(servicesContextPath.get("modify-save-sales-ms"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(sale)
                .retrieve()
                .toBodilessEntity();
    }

    private Mono<ResponseEntity<Void>> sendSaveCommands(List<Sale> sales) {
        return this.webClient
                .post()
                .uri(servicesContextPath.get("modify-save-sales-ms") + "multi")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(sales)
                .retrieve()
                .toBodilessEntity();
    }

    private Mono<ResponseEntity<Void>> sendDeleteCommand(Sale sale) {
        return this.webClient
                .post()
                .uri(servicesContextPath.get("modify-delete-sales-ms"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(sale)
                .retrieve()
                .toBodilessEntity();
    }

    private Mono<ResponseEntity<Void>> sendDeleteCommands(List<Sale> sales) {
        return this.webClient
                .post()
                .uri(servicesContextPath.get("modify-delete-sales-ms") + "multi")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(sales)
                .retrieve()
                .toBodilessEntity();
    }


}
