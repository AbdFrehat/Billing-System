package com.sales.reports.calc.price.services;

import com.sales.reports.calc.price.models.entites.Item;
import com.sales.reports.calc.price.models.responses.CalcPriceResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CalcPriceService {

    Mono<CalcPriceResponse> buildCalcPriceResponse(Flux<Item> items);

}
