package com.sales.receipt.generator.services.receipt;

import com.sales.receipt.generator.models.entities.Sale;
import com.sales.receipt.generator.services.jasper.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;


@Slf4j
@Service
public class ReceiptServiceImpl implements ReceiptService {

    private ResourceLoader resourceLoader;

    private ReportService reportService;

    public ReceiptServiceImpl(ResourceLoader resourceLoader, ReportService reportService) {
        this.resourceLoader = resourceLoader;
        this.reportService = reportService;
    }

    @Override
    public Mono<byte[]> generateReport(Sale sale) {
        Resource resource = resourceLoader.getResource("classpath:jasper/saleReceipt.jrxml");
        if (resource != null) {
            try {
                return Mono.just(reportService.createReport(sale, resource));
            } catch (IOException e) {
                log.error("An error happened while reading the report template: {}", e.getMessage());
            }
        } else {
            log.error("Jasper Report is not found in order to generate the Receipt");
        }
        return Mono.empty();
    }
}
