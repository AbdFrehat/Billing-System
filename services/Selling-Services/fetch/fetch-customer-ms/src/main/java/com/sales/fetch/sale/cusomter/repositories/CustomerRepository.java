package com.sales.fetch.sale.cusomter.repositories;

import com.sales.fetch.sale.cusomter.models.entites.Sale;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Sale, String> {

    @Query(value = "{ '_id' : ?0 }", fields = "{ 'customer' : 1 }")
    Mono<Sale> findCustomerBySaleId(String saleId);

}
