package com.selling.system.reports.generate.receipt.controllers;

import com.selling.system.reports.generate.receipt.services.receipt.ReceiptService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class ReceiptController {


    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<Mono<byte[]>> generateReceiptReport(@PathVariable("saleId") String saleId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        httpHeaders.setContentDispositionFormData("filename", "receipt-" + saleId + ".pdf");
        return ResponseEntity.ok().headers(httpHeaders).body(receiptService.generateReport(saleId));
    }
}
