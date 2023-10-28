package com.sales.reports.calc.price.services;

import com.sales.reports.calc.price.models.entites.Item;
import com.sales.reports.calc.price.models.responses.CalcPriceResponse;

import java.util.List;

public interface CalcPriceService {

    CalcPriceResponse buildCalcPriceResponse(List<Item> items);

}
