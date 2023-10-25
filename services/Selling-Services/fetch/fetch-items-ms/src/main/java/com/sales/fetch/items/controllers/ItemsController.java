package com.sales.fetch.items.controllers;

import com.sales.fetch.items.models.entities.Item;
import com.sales.fetch.items.services.ItemsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequestMapping("/items")
public class ItemsController {

    private ItemsService itemsService;

    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping
    public ResponseEntity<Mono<List<Item>>> getItems(@RequestParam @Valid String saleId) {
        return new ResponseEntity<>(itemsService.getItems(saleId), HttpStatus.OK);
    }
}
