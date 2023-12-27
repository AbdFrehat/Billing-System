package com.selling.system.data.get.manager.sales.sales.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selling.system.data.get.manager.sales.service.SalesClientService;
import com.selling.system.shared.module.models.commands.DataCommand;
import com.selling.system.shared.module.models.commands.QueryField;
import com.selling.system.shared.module.models.commands.SortField;
import com.selling.system.shared.module.models.enums.CommandType;
import com.selling.system.shared.module.models.enums.FieldType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;
import reactor.test.StepVerifier;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@AutoConfigureWireMock(port = 8000)
@TestPropertySource(
        properties = {
                "config.services.context-path.data-get-ms=http://localhost:8000/selling/data/get/query/sale/v1/",
                "config.services.context-path.data-get-free-ms=http://localhost:8000/selling/data/get/free/sale/v1/",
                "config.services.context-path.data-get-opt-ms=http://localhost:8000/selling/data/get/opt/sale/v1/",
                "spring.cloud.discovery.enabled=false"
        }
)
class SalesClientServiceImplTest {

    @Autowired
    private SalesClientService salesClientService;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("test if the request is sent successfully to sales-get-ms service")
    @Test
    void testSendRequest_ValidInput_ReturnQueryResponse_RoutingToGetSales() throws JsonProcessingException {
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
        stubFor(post(urlEqualTo("/selling/data/get/query/sale/v1/"))
                .withRequestBody(equalToJson(objectMapper.writeValueAsString(dataCommand)))
                .willReturn(
                        aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("sales.json")
                ));
        StepVerifier.create(salesClientService.sendRequest(dataCommand))
                .assertNext(verifiedQuerResponse -> {
                    assert verifiedQuerResponse.getData() != null;
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("test if the request is sent successfully to sales-get-free-ms service")
    void testSendRequest_ValidInput_ReturnQueryResponse_RoutingToGetOptSales() throws JsonProcessingException {
        DataCommand dataCommand = DataCommand.builder()
                .commandType(CommandType.GET_OPT_SALES)
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
        stubFor(post(urlEqualTo("/selling/data/get/opt/sale/v1/"))
                .withRequestBody(equalToJson(objectMapper.writeValueAsString(dataCommand)))
                .willReturn(
                        aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("sales.json")
                ));
        StepVerifier.create(salesClientService.sendRequest(dataCommand))
                .assertNext(verifiedQuerResponse -> {
                    assert verifiedQuerResponse.getData() != null;
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("test if the request is sent successfully to sales-get-opt-ms service")
    void testSendRequest_ValidInput_ReturnQueryResponse_RoutingToGetFreeSales() throws JsonProcessingException {
        DataCommand dataCommand = DataCommand.builder()
                .commandType(CommandType.GET_FREE_SALES)
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
        stubFor(post(urlEqualTo("/selling/data/get/free/sale/v1/"))
                .withRequestBody(equalToJson(objectMapper.writeValueAsString(dataCommand)))
                .willReturn(
                        aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("sales.json")
                ));
        StepVerifier.create(salesClientService.sendRequest(dataCommand))
                .assertNext(verifiedQuerResponse -> {
                    assert verifiedQuerResponse.getData() != null;
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("test if an exception is thrown if the query method is wrong or unsupported")
    void testSendRequest_ValidInput_ReturnQueryResponse_RoutingInvalidMethod() throws JsonProcessingException {
        DataCommand dataCommand = DataCommand.builder()
                .commandType(CommandType.SAVE_SALES)
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

        assertThrows(IllegalArgumentException.class, () -> salesClientService.sendRequest(dataCommand).subscribe());
    }


}