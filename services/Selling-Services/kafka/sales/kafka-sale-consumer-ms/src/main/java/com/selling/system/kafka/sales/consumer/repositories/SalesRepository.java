package com.selling.system.kafka.sales.consumer.repositories;


import com.selling.system.kafka.sales.consumer.entities.Sale;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SalesRepository extends ReactiveMongoRepository<Sale, String> {

}
