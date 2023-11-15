package com.selling.system.reports.generate.receipt.services.jasper;

import com.selling.system.reports.generate.receipt.models.entities.RecieptSale;
import org.springframework.core.io.Resource;

import java.io.IOException;

public interface ReportService {

    byte[] createReport(RecieptSale sale, Resource resource) throws IOException;
}
