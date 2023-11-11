package com.selling.system.shared.module.convertors;

import com.selling.system.shared.module.models.enums.PurchaseMethod;
import com.selling.system.shared.module.models.exceptions.PurchaseMethodNotFoundException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class StringToPurchaseMethodConvertor implements Converter<String, PurchaseMethod> {

    @Override
    public PurchaseMethod convert(@NonNull String purchaseMethodValue) {
        for (PurchaseMethod purchaseMethod : PurchaseMethod.values()) {
            if (purchaseMethod.getValue().equals(purchaseMethodValue)) {
                return purchaseMethod;
            }
        }
        throw new PurchaseMethodNotFoundException("Purchase Method Not Found");
    }
}
