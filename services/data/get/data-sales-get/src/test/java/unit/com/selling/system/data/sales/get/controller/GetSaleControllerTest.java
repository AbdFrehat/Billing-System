package com.selling.system.data.sales.get.controller;

import com.selling.system.data.shared.module.entites.SaleDocument;
import com.selling.system.data.shared.module.service.QueryResponseService;
import com.selling.system.shared.module.models.commands.DataCommand;
import com.selling.system.shared.module.models.commands.QueryField;
import com.selling.system.shared.module.models.commands.SortField;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.selling.system.data.shared.module.convertors.ObjectToSalesConvertor.toSales;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = GetSaleController.class)
@AutoConfigureWebTestClient
@Disabled
class GetSaleControllerTest {

    @MockBean
    private QueryResponseService queryResponseService;

    @Autowired
    private WebTestClient webTestClient;

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
                        SaleDocument.builder()
                                .id("1")
                                .saleDate(new Date())
                                .build()
                ))
                .build();
        when(queryResponseService.buildQueryResponse(dataCommand)).thenReturn(
                Mono.just(ResponseEntity.ok().body(dataResponse)));

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
                    List<SaleDocument> sales = toSales(queryResponseEntityExchangeResult.getResponseBody().getData());
                    assert sales != null;
                    assert sales.size() == 1;
                    assert sales.get(0).getId().equals("1");
                });
    }
}