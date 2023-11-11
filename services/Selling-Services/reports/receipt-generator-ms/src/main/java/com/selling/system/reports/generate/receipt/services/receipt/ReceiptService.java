package com.selling.system.reports.generate.receipt.services.receipt;

import com.selling.system.reports.generate.receipt.models.entities.Sale;
import reactor.core.publisher.Mono;

public interface ReceiptService {

    Mono<byte[]> generateReport(Sale sale);

}
