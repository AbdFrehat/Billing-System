package com.selling.system.reports.generate.receipt.client;

import com.orderizer.core.models.entities.Sale;
import reactor.core.publisher.Mono;

public interface DataManagerClient {

    Mono<Sale> retrieveSale(String saleId);
}
