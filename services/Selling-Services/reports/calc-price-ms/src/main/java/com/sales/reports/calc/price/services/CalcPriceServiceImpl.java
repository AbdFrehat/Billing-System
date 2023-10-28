package com.sales.reports.calc.price.services;

import com.sales.reports.calc.price.models.entites.Item;
import com.sales.reports.calc.price.models.responses.CalcPriceResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CalcPriceServiceImpl implements CalcPriceService {
    @Override
    public CalcPriceResponse buildCalcPriceResponse(List<Item> items) {
        return CalcPriceResponse
                .builder()
                .totalPrice(calculateTotalPrice(items))
                .totalQuantity(calculateTotalQuantity(items))
                .build();
    }

    private BigDecimal calculateTotalPrice(List<Item> items) {
        return items.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private int calculateTotalQuantity(List<Item> items) {
        return items.stream().mapToInt(Item::getQuantity).sum();
    }
}
