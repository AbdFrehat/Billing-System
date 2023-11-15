package com.selling.system.fetch.items.services;

import com.selling.system.fetch.items.models.entities.SaleDocument;
import com.selling.system.fetch.items.repositories.SalesRepository;
import com.selling.system.shared.module.models.entities.Item;
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
        return salesRepository.findItemsBySaleId(saleId).map(SaleDocument::getItems);
    }
}
