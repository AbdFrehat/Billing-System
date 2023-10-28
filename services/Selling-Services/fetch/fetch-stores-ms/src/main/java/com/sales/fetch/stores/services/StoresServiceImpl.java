package com.sales.fetch.stores.services;

import com.sales.fetch.stores.models.entities.Store;
import com.sales.fetch.stores.repositores.StoresRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StoresServiceImpl implements StoresService {

    private StoresRepository storesRepository;

    public StoresServiceImpl(StoresRepository storesRepository) {
        this.storesRepository = storesRepository;
    }

    @Override
    public Flux<Store> getStores() {
        return storesRepository.findAll();
    }
}
