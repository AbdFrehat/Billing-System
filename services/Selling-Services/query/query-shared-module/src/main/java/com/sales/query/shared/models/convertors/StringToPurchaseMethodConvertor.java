package com.sales.query.shared.models.convertors;

import com.selling.shared.models.enums.PurchaseMethod;
import org.springframework.core.convert.converter.Converter;

public class StringToPurchaseMethodConvertor implements Converter<String, PurchaseMethod> {

    @Override
    public PurchaseMethod convert(String source) {
        for (PurchaseMethod purchaseMethod : PurchaseMethod.values()) {
            if (purchaseMethod.getValue().equals(source)) {
                return purchaseMethod;
            }
        }
        return null;
    }
}
