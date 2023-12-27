package com.selling.system.data.sales.get.service;

import com.selling.system.data.shared.module.service.QueryBuilderService;
import com.selling.system.data.shared.module.service.interpreter.impl.ExpressionBuilderImpl;
import com.selling.system.shared.module.models.commands.DataCommand;
import com.selling.system.shared.module.models.commands.QueryField;
import com.selling.system.shared.module.models.commands.SortField;
import com.selling.system.shared.module.models.enums.CommandType;
import com.selling.system.shared.module.models.enums.FieldType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;
import java.util.regex.Pattern;

@SpringBootTest
@ContextConfiguration(classes = {
        QueryBuilderServiceImpl.class
})
class QueryBuilderServiceImplTest {

    @SpyBean
    private ExpressionBuilderImpl expressionBuilder;

    @Autowired
    private QueryBuilderService queryBuilderService;

    @DisplayName("test buildQuery method in QueryBuilderServiceImpl to check if the query object created successfully")
    @Test
    void testBuildQuery_ValidInput_WithoutCount_ReturnQuery() {
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
        Query query = queryBuilderService.buildQuery(dataCommand);
        System.out.println(query);
        assert query != null;
        assert query.getQueryObject().get("id").equals("1");
        assert query.isSorted();
        assert query.getSortObject().get("saleDate").equals(1);
        assert query.getLimit() == 10;
        assert query.getSkip() == 0;
    }

    @DisplayName("test buildQuery method in QueryBuilderServiceImpl to check if the query object created successfully with more than one queryField")
    @Test
    void testBuildQuery_ValidInput_WithoutCount_DifferentFields_ReturnQuery() {
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
        Query query = queryBuilderService.buildQuery(dataCommand);
        System.out.println(query);
        assert query != null;
        assert query.getQueryObject().get("id").equals("1");
        assert ((Pattern) query.getQueryObject().get("purchaseMethod")).pattern().equals("IN_STORE");
        assert query.isSorted();
        assert query.getSortObject().get("saleDate").equals(1);
        assert query.getLimit() == 20;
        assert query.getSkip() == 20;
    }

    @DisplayName("test buildQuery method in QueryBuilderServiceImpl to check if the query object created successfully with count request")
    @Test
    void testBuildQuery_ValidInput_WithCount_ReturnQuery() {
        DataCommand dataCommand = DataCommand.builder()
                .commandType(CommandType.GET_SALES)
                .queryFields(Map.of("F1", QueryField.builder()
                        .fieldType(FieldType.OTHER)
                        .value("1")
                        .field("id")
                        .build()))
                .excludedFields(new String[]{"items"})
                .payload(null)
                .count(true)
                .expression(null)
                .page(0)
                .size(10)
                .sortField(SortField.builder()
                        .field("saleDate")
                        .direction("ASC")
                        .build())
                .build();
        Query query = queryBuilderService.buildQuery(dataCommand);
        assert query != null;
        assert query.getQueryObject().get("id").equals("1");
        assert query.getSortObject().get("saleDate") == null;
        assert !query.isSorted();
        assert query.getLimit() == 0;
        assert query.getSkip() == 0;
    }
}