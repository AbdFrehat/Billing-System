package com.selling.system.data.sales.get.service;

import com.selling.system.data.shared.module.entites.SaleDocument;
import com.selling.system.data.shared.module.service.QueryResponseService;
import com.selling.system.data.shared.module.service.SalesService;
import com.selling.system.shared.module.models.commands.DataCommand;
import com.selling.system.shared.module.models.commands.QueryField;
import com.selling.system.shared.module.models.commands.SortField;
import com.selling.system.shared.module.models.enums.CommandType;
import com.selling.system.shared.module.models.enums.FieldType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {
        QueryResponseServiceImpl.class
})
class DataResponseServiceImplTest {

    @MockBean
    private SalesService salesService;

    @Autowired
    private QueryResponseService queryResponseService;

    @DisplayName("test buildQueryResponse method in queryResponseService to check if it returns a valid query response if the count option is disabled")
    @Test
    void testBuildQueryResponse_ValidInput_WithOutCount_ReturnQuery() {
        DataCommand dataCommand = DataCommand.builder()
                .commandType(CommandType.GET_SALES)
                .queryFields(Map.of("F1", QueryField.builder()
                                .fieldType(FieldType.OTHER)
                                .value("1")
                                .field("id")
                                .build(),
                        "F2", QueryField.builder()
                                .fieldType(FieldType.STRING)
                                .value("IN_STORE")
                                .field("purchaseMethod")
                                .build()))
                .excludedFields(new String[]{"items"})
                .payload(null)
                .count(false)
                .expression(null)
                .page(1)
                .size(20)
                .sortField(SortField.builder()
                        .field("saleDate")
                        .direction("ASC")
                        .build())
                .build();

        List<SaleDocument> sales = List.of(
                SaleDocument.builder()
                        .id("1")
                        .saleDate(new Date())
                        .purchaseMethod("IN_STORE")
                        .build());
        when(salesService.getSales(dataCommand))
                .thenReturn(Flux.fromIterable(sales));
        StepVerifier.create(queryResponseService.buildQueryResponse(dataCommand))
                .assertNext(verifiedQueryResponse -> {
                    assert verifiedQueryResponse.getBody() != null;
                    assert verifiedQueryResponse.getBody().getData().equals(sales);
                })
                .verifyComplete();
    }

    @DisplayName("test buildQueryResponse method in queryResponseService to check if it returns a valid query response if the count option is enabled")
    @Test
    void testBuildQueryResponse_ValidInput_WithCount_ReturnQuery() {
        DataCommand dataCommand = DataCommand.builder()
                .commandType(CommandType.GET_SALES)
                .queryFields(Map.of("F1", QueryField.builder()
                                .fieldType(FieldType.OTHER)
                                .value("1")
                                .field("id")
                                .build(),
                        "F2", QueryField.builder()
                                .fieldType(FieldType.STRING)
                                .value("IN_STORE")
                                .field("purchaseMethod")
                                .build()))
                .excludedFields(new String[]{"items"})
                .payload(null)
                .count(true)
                .expression(null)
                .page(1)
                .size(20)
                .sortField(SortField.builder()
                        .field("saleDate")
                        .direction("ASC")
                        .build())
                .build();
        when(salesService.count(dataCommand))
                .thenReturn(Mono.just(1L));
        StepVerifier.create(queryResponseService.buildQueryResponse(dataCommand))
                .assertNext(verifiedQueryResponse -> {
                    assert verifiedQueryResponse.getBody() != null;
                    assert verifiedQueryResponse.getBody().getData().equals(1L);
                })
                .verifyComplete();

    }
}