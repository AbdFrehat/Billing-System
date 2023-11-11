package com.selling.system.fetch.stores.services;

import com.selling.system.fetch.stores.models.entities.Store;
import reactor.core.publisher.Flux;

public interface StoresService {

    Flux<Store> getStores();

}
