package com.selling.system.reports.generate.receipt.client;

import com.selling.system.reports.generate.receipt.models.responses.CalcPriceResponse;
import com.orderizer.core.models.entities.Item;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CalcPriceClient {

    Mono<CalcPriceResponse> retrieveCalcPrice(List<Item> items);

}
