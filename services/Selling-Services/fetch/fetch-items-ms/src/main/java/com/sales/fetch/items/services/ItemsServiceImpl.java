package com.sales.fetch.items.services;

import com.sales.fetch.items.models.entities.Item;
import com.sales.fetch.items.models.entities.Sale;
import com.sales.fetch.items.repositories.SalesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class ItemsServiceImpl implements ItemsService {

    private SalesRepository salesRepository;

    public ItemsServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Override
    public Mono<List<Item>> getItems(String saleId) {
        return salesRepository.findItemsBySaleId(saleId).map(Sale::getItems);
    }
}
