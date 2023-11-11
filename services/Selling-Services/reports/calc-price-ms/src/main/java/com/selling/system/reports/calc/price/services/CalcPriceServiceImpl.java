package com.selling.system.reports.calc.price.services;

import com.selling.system.reports.calc.price.models.entites.Item;
import com.selling.system.reports.calc.price.models.responses.CalcPriceResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CalcPriceServiceImpl implements CalcPriceService {


    @Override
    public Mono<CalcPriceResponse> buildCalcPriceResponse(Flux<Item> items) {
        return Flux.from(items)
                .collectList()
                .flatMap(itemList ->
                        Mono.just(CalcPriceResponse.builder()
                                .totalPrice(getTotalPrice(itemList))
                                .totalQuantity(getTotalQuantity(itemList))
                                .build())
                );
    }

    private static Integer getTotalQuantity(List<Item> itemList) {
        return itemList.stream()
                .map(Item::getQuantity)
                .reduce(0, Integer::sum);
    }

    private static BigDecimal getTotalPrice(List<Item> itemList) {
        return itemList.stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
