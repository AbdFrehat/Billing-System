package com.selling.system.data.shared.module.repository;

import com.selling.system.data.shared.module.entites.SaleDocument;
import com.selling.system.data.shared.module.data.set.DataSet;
import com.selling.system.shared.module.models.enums.PurchaseMethod;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

/**
 * Integration Test for {@link SalesRepository} class which covers all database operation on Sale collection.
 *
 * @author Abd Frehat
 * @since 1.0
 */
@DataMongoTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = "com.selling.system.data.shared.module.repository")
class SalesRepositoryTest {


    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        mongoTemplate.insertAll(DataSet.SALES).blockLast();
    }

    @AfterEach
    void tearDown() {
        mongoTemplate.remove(new Query(), SaleDocument.class).block();
    }

    //Integration test for getSales SalesRepository method.
    @Test
    @Order(1)
    void getSales() {
        //Test when passing an empty Query object all sales documents are be retrieved
        Flux<SaleDocument> sales = salesRepository.getSales(new Query());
        StepVerifier.create(sales)
                .assertNext(sale -> {
                    assert sale.getId().equals("sale1");
                })
                .assertNext(sale -> {
                    assert sale.getId().equals("sale2");
                })
                .assertNext(sale -> {
                    assert sale.getId().equals("sale3");
                })
                .verifyComplete();
        //Test when requesting a specific id it will be returned
        Query query = new Query(Criteria.where("id").is("sale1"));
        sales = salesRepository.getSales(query);
        StepVerifier.create(sales)
                .assertNext(sale -> {
                    assert sale.getId() != null;
                    assert sale.getId().equals("sale1");
                })
                .verifyComplete();
    }

    //Integration test for saveSale SalesRepository method.
    @Test
    @Order(2)
    void saveSale() {
        SaleDocument sale = DataSet.SALES.get(0);
        sale.setId("sale4");
        this.salesRepository.saveSale(sale).block();
        Flux<SaleDocument> sales = this.salesRepository
                .getSales(new Query(Criteria.where("id").is("sale4")));
        StepVerifier.create(sales)
                .assertNext(insertedSale -> {
                    assert insertedSale != null;
                    assert insertedSale.getId().equals("sale4");
                }).verifyComplete();
    }

    //Integration test for saveSales SalesRepository method.
    @Test
    @Order(3)
    void saveSales() throws InterruptedException {
        SaleDocument sale1 = DataSet.SALES.get(0);
        sale1.setId("sale5");
        SaleDocument sale2 = DataSet.SALES.get(1);
        sale2.setId("sale6");
        SaleDocument sale3 = DataSet.SALES.get(2);
        sale3.setId("sale7");
        List<SaleDocument> sales = List.of(sale1, sale2, sale3);
        this.salesRepository.saveSales(sales).blockLast();
        Flux<SaleDocument> insertedSales = salesRepository.getSales(new Query());
        StepVerifier.create(insertedSales)
                .expectNextCount(6)
                .verifyComplete();
    }

    //Integration test for updateSale SalesRepository method.
    @Test
    @Order(4)
    void updateSale() {
        SaleDocument sale = DataSet.SALES.get(0);
        sale.setId("sale8");
        sale = this.salesRepository
                .saveSale(sale)
                .block();
        assert sale != null;
        sale.setPurchaseMethod(PurchaseMethod.PHONE.getValue());
        this.salesRepository.updateSale(sale).block();
        Flux<SaleDocument> sales = this.salesRepository
                .getSales(new Query(Criteria.where("id").is("sale8")));
        StepVerifier.create(sales)
                .assertNext(s -> {
                    assert s != null;
                    assert s.getPurchaseMethod().equals(PurchaseMethod.PHONE.getValue());
                }).verifyComplete();
    }

    //Integration test for deleteSale SalesRepository method.
    @Test
    @Order(5)
    void deleteSale() {
        SaleDocument sale = DataSet.SALES.get(0);
        sale.setId("sale9");
        sale = this.salesRepository
                .saveSale(sale)
                .block();
        assert sale != null;
        sale = this.salesRepository
                .getSales(new Query(Criteria.where("id").is("sale9")))
                .blockFirst();
        assert sale != null;
        this.salesRepository.deleteSale(sale).block();
        Flux<SaleDocument> sales = this.salesRepository
                .getSales(new Query(Criteria.where("id").is("sale9")));
        StepVerifier.create(sales)
                .verifyComplete();
    }

    //Integration test for deleteSales SalesRepository method.
    @Test
    @Order(6)
    void deleteSales() {
        this.salesRepository.deleteSales(DataSet.SALES).block();
        Flux<SaleDocument> sales = this.salesRepository.getSales(new Query());
        StepVerifier.create(sales)
                .verifyComplete();
    }
}