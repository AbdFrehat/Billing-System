package com.sales.fetch.stores.services;

import com.sales.fetch.stores.models.entities.Store;
import reactor.core.publisher.Flux;

public interface StoresService {

    Flux<Store> getStores();

}
