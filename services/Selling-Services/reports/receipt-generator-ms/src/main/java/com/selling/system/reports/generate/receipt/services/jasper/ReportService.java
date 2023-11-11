package com.selling.system.reports.generate.receipt.services.jasper;

import com.selling.system.reports.generate.receipt.models.entities.Sale;
import org.springframework.core.io.Resource;

import java.io.IOException;

public interface ReportService {

    byte[] createReport(Sale sale, Resource resource) throws IOException;
}
