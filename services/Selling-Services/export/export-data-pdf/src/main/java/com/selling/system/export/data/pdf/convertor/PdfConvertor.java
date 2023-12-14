package com.selling.system.export.data.pdf.convertor;


import com.selling.system.export.data.pdf.mapper.JasperMapper;
import com.selling.system.export.shared.convertor.DataConvertor;
import com.selling.system.shared.module.models.commands.ExportDataCommand;
import com.selling.system.shared.module.models.entities.Sale;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;


@Component
public class PdfConvertor implements DataConvertor {

    private final Map<String, JasperReport> jasperReportMap;

    private final JasperMapper jasperMapper;

    public PdfConvertor(Map<String, JasperReport> jasperReportMap, JasperMapper jasperMapper) {
        this.jasperReportMap = jasperReportMap;
        this.jasperMapper = jasperMapper;
    }

    @Override
    public Mono<byte[]> convert(Flux<Sale> salesFlux, ExportDataCommand exportDataCommand) {
        return salesFlux.collectList().map(sales -> jasperMapper.map(sales, exportDataCommand)).<JasperPrint>handle((sheetParameters, sink) -> {
                    try {
                        sink.next(JasperFillManager.fillReport(jasperReportMap.get("sales"), sheetParameters, new JREmptyDataSource()));
                    } catch (JRException e) {
                        sink.error(new RuntimeException(e));
                    }
                })
                .handle((jasperPrint, sink) -> {
                    try {
                        sink.next(JasperExportManager.exportReportToPdf(jasperPrint));
                    } catch (JRException e) {
                        sink.error(new RuntimeException(e));
                    }
                });
    }

}
