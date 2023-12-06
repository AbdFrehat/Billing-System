package com.selling.system.reports.generate.receipt.services.receipt;

import com.selling.system.reports.generate.receipt.models.entities.RecieptSale;
import com.selling.system.shared.module.models.entities.Sale;
import reactor.core.publisher.Mono;

public interface ReceiptService {

    Mono<byte[]> generateReport(String saleId);

}
