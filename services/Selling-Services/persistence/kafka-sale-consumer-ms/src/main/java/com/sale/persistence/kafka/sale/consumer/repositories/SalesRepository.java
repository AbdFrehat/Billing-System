package com.sale.persistence.kafka.sale.consumer.repositories;


import com.sale.persistence.kafka.sale.consumer.entities.Sale;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SalesRepository extends ReactiveMongoRepository<Sale, String> {

}
