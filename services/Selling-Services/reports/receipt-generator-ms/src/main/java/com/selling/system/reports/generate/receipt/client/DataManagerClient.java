package com.selling.system.reports.generate.receipt.client;

import com.selling.system.shared.module.models.entities.Sale;
import reactor.core.publisher.Mono;

public interface DataManagerClient {

    Mono<Sale> retrieveSale(String saleId);
}
