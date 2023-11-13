package com.selling.system.query.shared.module.service;

import com.selling.system.query.shared.module.data.set.DataSet;
import com.selling.system.query.shared.module.entites.Sale;
import com.selling.system.shared.module.models.commands.QueryCommand;
import com.selling.system.shared.module.models.commands.QueryField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

@DataMongoTest
@ComponentScan(basePackages = "com.selling.system.query.shared.module")
class SalesServiceTest {

    @Autowired
    private SalesService salesService;

    @BeforeEach
    void setUp() {
        salesService.saveSales(DataSet.SALES).blockLast();
    }

    @AfterEach
    void tearDown() {
        salesService.deleteSales(new Query()).block();
    }

    //Integration test for getSales SalesService method when querying the documents with specific id.
    @Test
    @Order(1)
    void getSalesById() {
        QueryCommand queryCommand = new QueryCommand();
        List<QueryField> queryFields = List.of(
                DataSet.QUERY_FIELD_MAP.get("sale.id")
        );
        queryCommand.setQueryFields(queryFields);
        Flux<Sale> sales = salesService.getSales(queryCommand);
        StepVerifier.create(sales)
                .assertNext(sale -> {
                    assert sale.getId().equals("sale1");
                })
                .verifyComplete();
    }

    @Test
    @Order(2)
    void getSalesByItemsTags() {
        QueryCommand queryCommand = new QueryCommand();
        List<QueryField> queryFields = List.of(
                DataSet.QUERY_FIELD_MAP.get("sale.items.tags")
        );
        queryCommand.setQueryFields(queryFields);
        Flux<Sale> sales = salesService.getSales(queryCommand);
        StepVerifier.create(sales)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    @Order(3)
    void getSalesByItemsPrice() {
        QueryCommand queryCommand = new QueryCommand();
        List<QueryField> queryFields = List.of(
                DataSet.QUERY_FIELD_MAP.get("sale.item.price")
        );
        queryCommand.setQueryFields(queryFields);
        Flux<Sale> sales = salesService.getSales(queryCommand);
        StepVerifier.create(sales)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    @Order(4)
    void getSalesByItemsPriceRange() {
        QueryCommand queryCommand = new QueryCommand();
        List<QueryField> queryFields = List.of(
                DataSet.QUERY_FIELD_MAP.get("sale.item.price.range")
        );
        queryCommand.setQueryFields(queryFields);
        Flux<Sale> sales = salesService.getSales(queryCommand);
        StepVerifier.create(sales)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    @Order(5)
    void getSalesByCustomersAge() {
        QueryCommand queryCommand = new QueryCommand();
        List<QueryField> queryFields = List.of(
                DataSet.QUERY_FIELD_MAP.get("sale.customer.age")
        );
        queryCommand.setQueryFields(queryFields);
        Flux<Sale> sales = salesService.getSales(queryCommand);
        StepVerifier.create(sales)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    @Order(5)
    void getSalesByCustomersAgeAndStoreLocation() {
        QueryCommand queryCommand = new QueryCommand();
        List<QueryField> queryFields = List.of(
                DataSet.QUERY_FIELD_MAP.get("sale.customer.age"),
                DataSet.QUERY_FIELD_MAP.get("sale.storeLocation")
        );
        queryCommand.setQueryFields(queryFields);
        Flux<Sale> sales = salesService.getSales(queryCommand);
        StepVerifier.create(sales)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    @Order(6)
    void getSalesByCustomersAgeWithPagination() {
        QueryCommand queryCommand = new QueryCommand();
        List<QueryField> queryFields = List.of(
                DataSet.QUERY_FIELD_MAP.get("sale.customer.age")
        );
        queryCommand.setQueryFields(queryFields);
        queryCommand.setPage(0);
        queryCommand.setSize(1);
        Flux<Sale> sales = salesService.getSales(queryCommand);
        StepVerifier.create(sales)
                .expectNextCount(1)
                .verifyComplete();
        queryCommand.setQueryFields(queryFields);
        queryCommand.setPage(1);
        queryCommand.setSize(1);
        sales = salesService.getSales(queryCommand);
        StepVerifier.create(sales)
                .expectNextCount(1)
                .verifyComplete();
    }

}