package com.sales.fetch.stores.repositores;

import com.sales.fetch.stores.models.entities.Store;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StoresRepository extends ReactiveMongoRepository<Store, String> {
}
