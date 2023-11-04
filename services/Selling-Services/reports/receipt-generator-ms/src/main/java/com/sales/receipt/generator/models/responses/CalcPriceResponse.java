package com.sales.receipt.generator.models.responses;

import com.selling.shared.models.responses.AbstractCalcPriceResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalcPriceResponse implements AbstractCalcPriceResponse {

    private BigDecimal totalPrice;

    private int totalQuantity;

}
