package com.selling.system.reports.calc.price.services;

import com.selling.system.reports.calc.price.models.responses.CalcPriceResponse;
import com.selling.system.shared.module.models.entities.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CalcPriceService {

    Mono<CalcPriceResponse> buildCalcPriceResponse(Flux<Item> items);

}
