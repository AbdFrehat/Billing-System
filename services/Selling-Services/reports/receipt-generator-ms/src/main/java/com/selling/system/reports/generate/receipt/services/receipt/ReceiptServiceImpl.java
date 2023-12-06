package com.selling.system.reports.generate.receipt.services.receipt;

import com.selling.system.reports.generate.receipt.client.CalcPriceClient;
import com.selling.system.reports.generate.receipt.client.DataManagerClient;
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

    private final DataManagerClient dataManagerClient;

    public ReceiptServiceImpl(ResourceLoader resourceLoader, ReportService reportService,
                              CalcPriceClient calcPriceClient, DataManagerClient dataManagerClient) {
        this.resourceLoader = resourceLoader;
        this.reportService = reportService;
        this.calcPriceClient = calcPriceClient;
        this.dataManagerClient = dataManagerClient;
    }

    @Override
    public Mono<byte[]> generateReport(String saleIdle) {
        Resource resource = resourceLoader.getResource("classpath:jasper/saleReceipt.jrxml");
        return dataManagerClient.retrieveSale(saleIdle)
                .flatMap(sale -> calcPriceClient.retrieveCalcPrice(sale.getItems())
                        .flatMap(calcPriceResponse -> {
                            try {
                                return Mono.just(reportService.createReport(sale, calcPriceResponse, resource));
                            } catch (IOException e) {
                                log.error("Jasper Report is not found in order to generate the Receipt");
                            }
                            return Mono.empty();
                        })
                );
    }
}
