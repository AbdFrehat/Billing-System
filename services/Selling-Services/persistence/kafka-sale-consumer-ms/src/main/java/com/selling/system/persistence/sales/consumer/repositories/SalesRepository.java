package com.selling.system.persistence.sales.consumer.repositories;


import com.selling.system.persistence.sales.consumer.entities.Sale;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SalesRepository extends ReactiveMongoRepository<Sale, String> {

}
