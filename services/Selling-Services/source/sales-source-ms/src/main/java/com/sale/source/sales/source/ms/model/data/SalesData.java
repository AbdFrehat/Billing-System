package com.sale.source.sales.source.ms.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesData {
    private List<String> purchaseMethodValues;

    private List<String> storeLocationValues;

    private List<Boolean> couponUsedValues;

    private ItemsData itemsData;

    private CustomerData customerData;

}
