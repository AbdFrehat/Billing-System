package com.sales.fetch.stores.controllers;

import com.sales.fetch.stores.models.entities.Store;
import com.sales.fetch.stores.services.StoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StoresController {

    private StoresService storesService;

    public StoresController(StoresService storesService) {
        this.storesService = storesService;
    }

    @GetMapping
    public ResponseEntity<Flux<Store>> getStores() {
        return new ResponseEntity(storesService.getStores(), HttpStatus.OK);
    }

}
