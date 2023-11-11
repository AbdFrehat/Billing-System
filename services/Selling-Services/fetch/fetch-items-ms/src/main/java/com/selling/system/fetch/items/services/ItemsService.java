package com.selling.system.fetch.items.services;

import com.selling.system.fetch.items.models.entities.Item;
import reactor.core.publisher.Mono;
import java.util.List;


public interface ItemsService {

    Mono<List<Item>> getItems(String saleId);
}
