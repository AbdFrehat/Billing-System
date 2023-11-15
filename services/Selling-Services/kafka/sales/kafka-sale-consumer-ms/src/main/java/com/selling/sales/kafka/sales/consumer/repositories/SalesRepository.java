package com.selling.sales.kafka.sales.consumer.repositories;


import com.selling.sales.kafka.sales.consumer.entities.SaleDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SalesRepository extends ReactiveMongoRepository<SaleDocument, String> {

}
