package com.selling.system.shared.module.models.responses;

import java.math.BigDecimal;

public interface AbstractCalcPriceResponse {

    BigDecimal getTotalPrice();

    int getTotalQuantity();

}
