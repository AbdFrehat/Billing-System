package com.selling.system.sales.reports.calc.price.services;

import com.selling.system.sales.reports.calc.price.models.responses.CalcPriceResponse;
import com.selling.system.sales.reports.calc.price.models.entites.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CalcPriceService {

    Mono<CalcPriceResponse> buildCalcPriceResponse(Flux<Item> items);

}
