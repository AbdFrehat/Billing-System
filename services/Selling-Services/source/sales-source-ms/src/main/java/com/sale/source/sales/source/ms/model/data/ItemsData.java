package com.sale.source.sales.source.ms.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemsData {

    private List<String> nameValues;

    private PriceRange priceRange;

    private List<String> tagsValues;

    private QuantityRange quantityRange;


    public static class PriceRange extends Range {

    }

    public static class QuantityRange extends Range {
    }

}




