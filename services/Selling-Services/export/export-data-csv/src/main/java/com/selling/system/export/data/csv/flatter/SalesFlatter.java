package com.selling.system.export.data.csv.flatter;

import com.selling.system.export.data.csv.model.FlattedSale;
import com.selling.system.shared.module.models.entities.Sale;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class SalesFlatter {
    private static final ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public List<FlattedSale> flat(List<Sale> sales) {
        return sales.stream().flatMap(sale -> sale.getItems().stream().map(item ->
                FlattedSale.builder()
                        .id(sale.getId())
                        .saleDate(dateFormatThreadLocal.get().format(sale.getSaleDate()))
                        .couponUsed(sale.isCouponUsed())
                        .storeLocation(sale.getStoreLocation())
                        .purchaseMethod(sale.getPurchaseMethod())
                        .customerSatisfaction(sale.getCustomer().getSatisfaction())
                        .customerGender(sale.getCustomer().getGender())
                        .customerEmail(sale.getCustomer().getGender())
                        .customerAge(sale.getCustomer().getAge())
                        .itemQuantity(item.getQuantity())
                        .itemName(item.getName())
                        .itemPrice(item.getPrice())
                        .itemTags(String.join("-", item.getTags()))
                        .build()
        )).toList();
    }

}
