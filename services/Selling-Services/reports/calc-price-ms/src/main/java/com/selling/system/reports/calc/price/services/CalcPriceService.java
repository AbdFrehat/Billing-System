package com.selling.system.reports.calc.price.services;

import com.selling.system.reports.calc.price.models.entites.Item;
import com.selling.system.reports.calc.price.models.responses.CalcPriceResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CalcPriceService {

    Mono<CalcPriceResponse> buildCalcPriceResponse(Flux<Item> items);

}
