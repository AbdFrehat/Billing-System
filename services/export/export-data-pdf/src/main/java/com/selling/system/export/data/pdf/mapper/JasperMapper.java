package com.selling.system.export.data.pdf.mapper;

import com.selling.system.export.data.pdf.model.dto.ItemDTO;
import com.selling.system.export.data.pdf.model.dto.SaleDTO;
import com.selling.system.export.data.pdf.model.dto.TagDTO;
import com.orderizer.core.models.commands.ExportDataFilter;
import com.orderizer.core.models.entities.Customer;
import com.orderizer.core.models.entities.Item;
import com.orderizer.core.models.entities.Sale;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JasperMapper {

    private final Map<String, JasperReport> jasperReportMap;

    public JasperMapper(Map<String, JasperReport> jasperReportMap) {
        this.jasperReportMap = jasperReportMap;
    }

    public Map<String, Object> map(List<Sale> sales, ExportDataFilter command) {
        Map<String, Object> reportParameters = new HashMap<>();
        addHeaderParameters(command, reportParameters, sales.size());
        reportParameters.put("salesDS", createSalesDS(sales));
        return reportParameters;
    }

    private JRBeanCollectionDataSource createSalesDS(List<Sale> sales) {
        return new JRBeanCollectionDataSource(sales.stream()
                .map(sale -> SaleDTO.builder()
                        .saleDate(sale.getSaleDate())
                        .couponUsed(sale.isCouponUsed())
                        .storeLocation(sale.getStoreLocation())
                        .purchaseMethod(sale.getPurchaseMethod())
                        .customerReport(jasperReportMap.get("customer"))
                        .customerParameters(createCustomerParameters(sale))
                        .customerDS(new JREmptyDataSource())
                        .itemsReport(jasperReportMap.get("items"))
                        .itemsDS(new JREmptyDataSource())
                        .itemsParameters(createItemsParameters(sale))
                        .build())
                .toList()

        );
    }

    private static Map<String, Object> createCustomerParameters(Sale sale) {
        Map<String, Object> customerParameters = new HashMap<>();
        customerParameters.put("customerDS", new JRBeanCollectionDataSource((new ArrayList<Customer>() {{
            add(sale.getCustomer());
        }})));
        return customerParameters;
    }

    private static Map<String, Object> createItemsParameters(Sale sale) {
        return new HashMap<>(Map.of(
                "itemsDS", new JRBeanCollectionDataSource(sale.getItems()
                        .stream()
                        .map(item -> ItemDTO.builder()
                                .name(item.getName())
                                .price(item.getPrice())
                                .quantity(item.getQuantity())
                                .tagsDS(new JRBeanCollectionDataSource(item.getTags().stream()
                                        .map(TagDTO::new)
                                        .toList()))
                                .build())
                        .toList()),
                "totalPrice", sale.getItems().stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add),
                "totalQuantity", sale.getItems().stream().map(Item::getQuantity).reduce(0, Integer::sum)
        ));
    }

    private static void addHeaderParameters(ExportDataFilter command, Map<String, Object> reportParameters, long total) {
        reportParameters.put("fromDate", command.getFromDate());
        reportParameters.put("toDate", command.getToDate());
        reportParameters.put("storeLocation", command.getStoreLocation());
        reportParameters.put("purchaseMethod", command.getPurchaseMethod());
        reportParameters.put("satisfaction", command.getSatisfaction());
        reportParameters.put("couponUsed", command.getCouponUsed());
        reportParameters.put("total", total);
    }
}
