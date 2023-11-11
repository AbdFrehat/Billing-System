package com.selling.system.shared.models.responses;

import java.math.BigDecimal;

public interface AbstractCalcPriceResponse {

    BigDecimal getTotalPrice();

    int getTotalQuantity();

}
