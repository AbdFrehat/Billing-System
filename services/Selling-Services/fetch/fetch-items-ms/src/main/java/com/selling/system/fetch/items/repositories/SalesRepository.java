package com.selling.system.fetch.items.repositories;

import com.selling.system.fetch.items.models.entities.Sale;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface SalesRepository extends ReactiveMongoRepository<Sale, String> {

    @Query(value = "{ '_id' : ?0 }", fields = "{ 'items' : 1 }")
    Mono<Sale> findItemsBySaleId(String saleId);

}
