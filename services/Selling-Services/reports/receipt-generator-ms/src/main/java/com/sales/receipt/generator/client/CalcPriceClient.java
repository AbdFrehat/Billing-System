package com.sales.receipt.generator.client;

import com.sales.receipt.generator.models.entities.Item;
import com.sales.receipt.generator.models.responses.CalcPriceResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CalcPriceClient {

    Mono<CalcPriceResponse> retrieveCalcPrice(List<Item> items);

}
