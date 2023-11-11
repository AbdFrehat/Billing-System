package com.selling.system.source.random.generator.sales.model.data;

import com.selling.system.shared.module.models.commons.Range;
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


    public static class PriceRange extends Range<Integer> {

    }

    public static class QuantityRange extends Range<Integer> {
    }

}




