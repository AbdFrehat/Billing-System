package com.selling.system.fetch.stores.services;

import com.selling.system.fetch.stores.repositores.StoresRepository;
import com.selling.system.fetch.stores.models.entities.Store;
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
