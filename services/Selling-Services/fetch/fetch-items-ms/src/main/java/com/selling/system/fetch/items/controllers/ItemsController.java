package com.selling.system.fetch.items.controllers;

import com.selling.system.fetch.items.services.ItemsService;
import com.selling.system.shared.module.models.entities.Item;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
public class ItemsController {

    private final ItemsService itemsService;

    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping("{saleId}")
    public ResponseEntity<Mono<List<Item>>> getItems(@PathVariable @Valid String saleId) {
        return new ResponseEntity<>(itemsService.getItems(saleId), HttpStatus.OK);
    }
}
