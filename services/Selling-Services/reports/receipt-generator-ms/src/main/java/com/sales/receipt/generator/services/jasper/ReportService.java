package com.sales.receipt.generator.services.jasper;

import com.sales.receipt.generator.models.entities.Sale;
import org.springframework.core.io.Resource;

import java.io.IOException;

public interface ReportService {

    byte[] createReport(Sale sale, Resource resource) throws IOException;
}
