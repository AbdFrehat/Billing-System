package com.selling.system.reports.generate.receipt.services.receipt;


import reactor.core.publisher.Mono;

public interface ReceiptService {

    Mono<byte[]> generateReport(String saleId);

}
