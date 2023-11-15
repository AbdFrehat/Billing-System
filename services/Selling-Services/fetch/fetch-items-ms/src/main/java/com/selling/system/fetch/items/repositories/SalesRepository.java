package com.selling.system.fetch.items.repositories;

import com.selling.system.fetch.items.models.entities.SaleDocument;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface SalesRepository extends ReactiveMongoRepository<SaleDocument, String> {

    @Query(value = "{ '_id' : ?0 }", fields = "{ 'items' : 1 }")
    Mono<SaleDocument> findItemsBySaleId(String saleId);

}
