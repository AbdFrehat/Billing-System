package com.selling.system.data.get.manager.sales.controller;

import com.selling.system.data.get.manager.sales.service.SalesClientService;
import com.selling.system.shared.module.models.commands.DataCommand;
import com.selling.system.shared.module.models.commands.QueryField;
import com.selling.system.shared.module.models.commands.SortField;
import com.selling.system.shared.module.models.entities.Sale;
import com.selling.system.shared.module.models.enums.CommandType;
import com.selling.system.shared.module.models.enums.FieldType;
import com.selling.system.shared.module.models.responses.DataResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;


@WebFluxTest(controllers = SalesRouterController.class)
@AutoConfigureWebTestClient
@Disabled
class SalesRouterControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private SalesClientService salesClientService;


    @Test
    void testGetSales_ValidInput_ReturnQueryResponse() {
        DataCommand dataCommand = DataCommand.builder()
                .commandType(CommandType.GET_SALES)
                .queryFields(Map.of("F1", QueryField.builder()
                        .fieldType(FieldType.OTHER)
                        .value("1")
                        .field("id")
                        .build()))
                .excludedFields(new String[]{"items"})
                .payload(null)
                .count(false)
                .expression(null)
                .page(0)
                .size(10)
                .sortField(SortField.builder()
                        .field("saleDate")
                        .direction("ASC")
                        .build())
                .build();
        DataResponse dataResponse = DataResponse.builder()
                .data(List.of(
                        Sale.builder()
                                .id("1")
                                .saleDate(new Date())
                                .build()
                ))
                .build();
        when(salesClientService.sendRequest(dataCommand))
                .thenReturn(Mono.just(dataResponse));
        webTestClient
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dataCommand)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(DataResponse.class)
                .consumeWith(queryResponseEntityExchangeResult -> {
                    assert queryResponseEntityExchangeResult.getResponseBody() != null;
                    assert queryResponseEntityExchangeResult.getResponseBody().getData() != null;
                });

    }

}