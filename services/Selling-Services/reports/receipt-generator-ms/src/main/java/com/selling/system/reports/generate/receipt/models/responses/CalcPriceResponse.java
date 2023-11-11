package com.selling.system.reports.generate.receipt.models.responses;

import com.selling.system.shared.models.responses.AbstractCalcPriceResponse;
import lombok.AllArgsConstructor;
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
