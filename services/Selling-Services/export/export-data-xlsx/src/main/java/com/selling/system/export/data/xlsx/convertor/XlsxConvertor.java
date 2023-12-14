package com.selling.system.export.data.xlsx.convertor;


import com.selling.system.export.data.xlsx.mapper.JasperMapper;
import com.selling.system.export.shared.convertor.DataConvertor;
import com.selling.system.shared.module.models.commands.ExportDataCommand;
import com.selling.system.shared.module.models.entities.Sale;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.util.Map;


@Component
public class XlsxConvertor implements DataConvertor {

    private final Map<String, JasperReport> jasperReportMap;

    private final JasperMapper jasperMapper;

    public XlsxConvertor(Map<String, JasperReport> jasperReportMap, JasperMapper jasperMapper) {
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
                        JRXlsxExporter exporter = new JRXlsxExporter();
                        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
                        exporter.exportReport();
                        sink.next(byteArrayOutputStream.toByteArray());
                    } catch (JRException e) {
                        sink.error(new RuntimeException(e));
                    }
                });
    }

}
