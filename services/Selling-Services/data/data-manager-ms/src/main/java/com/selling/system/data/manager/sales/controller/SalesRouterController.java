package com.selling.system.data.manager.sales.controller;

import com.selling.system.data.manager.sales.client.SalesClientService;
import com.selling.system.shared.module.models.commands.DataCommand;
import com.selling.system.shared.module.models.responses.QueryResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * The main controller class in the query-manager-ms, it exposes one endpoint for receiving data requests from the system.
 *
 * @author Abd Frehat
 * @since 1.0
 */
@RestController
public class SalesRouterController {


    private final SalesClientService salesClientService;

    public SalesRouterController(SalesClientService salesClientService) {
        this.salesClientService = salesClientService;
    }

    /**
     * This endpoint is responsible for receiving a query command from any service needs to operations on the database.
     * takes the request and handles it to the salesClientService which will call the suitable service for doing the desired operation.
     *
     * @param dataCommand {@link DataCommand}
     * @return {@link Mono}<{@link ResponseEntity}<{@link QueryResponse}>>
     */
    @PostMapping
    public Mono<ResponseEntity<QueryResponse>> routeDataCommand(@RequestBody @Valid DataCommand dataCommand) {
        return salesClientService.sendRequest(dataCommand)
                .map(queryResponse -> ResponseEntity.ok().body(queryResponse));
    }

    @PostMapping("/multi")
    public ResponseEntity<Void> routeDataCommands(@RequestBody @Valid List<DataCommand> dataCommands) {
        dataCommands.forEach(dataCommand -> salesClientService.sendRequest(dataCommand).subscribe());
        return ResponseEntity.ok().build();
    }


}
