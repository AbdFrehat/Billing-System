package com.selling.system.reports.generate.receipt.controllers;

import com.selling.system.reports.generate.receipt.models.entities.RecieptSale;
import com.selling.system.reports.generate.receipt.services.receipt.ReceiptService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class ReceiptController {


    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping
    public ResponseEntity<Mono<byte[]>> generateReceiptReport(@RequestBody RecieptSale sale) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        httpHeaders.setContentDispositionFormData("filename", "receipt-" + sale.getId() + ".pdf");
        return new ResponseEntity(receiptService.generateReport(sale), httpHeaders, HttpStatus.OK);
    }
}
