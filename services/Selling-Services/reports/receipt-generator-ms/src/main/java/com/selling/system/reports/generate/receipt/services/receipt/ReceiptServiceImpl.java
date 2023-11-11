package com.selling.system.reports.generate.receipt.services.receipt;

import com.selling.system.reports.generate.receipt.client.CalcPriceClient;
import com.selling.system.reports.generate.receipt.models.entities.Sale;
import com.selling.system.reports.generate.receipt.models.responses.CalcPriceResponse;
import com.selling.system.reports.generate.receipt.services.jasper.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;


@Slf4j
@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ResourceLoader resourceLoader;

    private final ReportService reportService;

    private final CalcPriceClient calcPriceClient;

    public ReceiptServiceImpl(ResourceLoader resourceLoader, ReportService reportService, CalcPriceClient calcPriceClient) {
        this.resourceLoader = resourceLoader;
        this.reportService = reportService;
        this.calcPriceClient = calcPriceClient;
    }

    @Override
    public Mono<byte[]> generateReport(Sale sale) {
        Resource resource = resourceLoader.getResource("classpath:jasper/saleReceipt.jrxml");
        return retrieveCalcPriceResponse(sale).flatMap(calcPriceResponse -> {
            try {
                return Mono.just(reportService.createReport(sale, resource));
            } catch (IOException e) {
                log.error("Jasper Report is not found in order to generate the Receipt");
            }
            return Mono.empty();
        });
    }

    private Mono<CalcPriceResponse> retrieveCalcPriceResponse(Sale sale) {
        return calcPriceClient.retrieveCalcPrice(sale.getItems()).doOnSuccess(calcPriceResponse -> {
            sale.setTotalQuantity(calcPriceResponse.getTotalQuantity());
            sale.setTotalPrice(calcPriceResponse.getTotalPrice());
        });
    }
}
