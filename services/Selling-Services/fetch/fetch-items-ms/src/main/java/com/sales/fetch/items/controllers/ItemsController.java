package com.sales.fetch.items.controllers;

import com.sales.fetch.items.models.entities.Item;
import com.sales.fetch.items.services.ItemsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
public class ItemsController {

    private ItemsService itemsService;

    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping("{saleId}")
    public ResponseEntity<Mono<List<Item>>> getItems(@PathVariable @Valid String saleId) {
        return new ResponseEntity<>(itemsService.getItems(saleId), HttpStatus.OK);
    }
}
