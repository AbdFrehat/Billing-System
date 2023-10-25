package com.sales.fetch.items.services;

import com.sales.fetch.items.models.entities.Item;
import reactor.core.publisher.Mono;
import java.util.List;


public interface ItemsService {

    Mono<List<Item>> getItems(String saleId);
}
