package com.selling.system.fetch.stores.repositores;

import com.selling.system.fetch.stores.models.entities.Store;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StoresRepository extends ReactiveMongoRepository<Store, String> {
}
