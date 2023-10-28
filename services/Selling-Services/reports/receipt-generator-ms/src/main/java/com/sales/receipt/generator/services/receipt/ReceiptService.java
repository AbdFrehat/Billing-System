package com.sales.receipt.generator.services.receipt;

import com.sales.receipt.generator.models.entities.Sale;
import reactor.core.publisher.Mono;

public interface ReceiptService {

    Mono<byte[]> generateReport(Sale sale);

}
