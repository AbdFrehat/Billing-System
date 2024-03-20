package com.orderizer.data.stores.manager.repository.api;

import com.mongodb.client.result.DeleteResult;
import com.orderizer.data.stores.manager.model.entity.Store;
import com.orderizer.data.stores.manager.model.request.SaveStoreRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoresRepository {

    Mono<Store> saveStore(SaveStoreRequest request);

    Mono<Store> updateStore(Store store);

    Mono<Store> retrieveStoreByIdentifier(int identifier);

    Mono<DeleteResult> deleteStore(int identifier);

    Flux<Store> retrieveStores();
}
