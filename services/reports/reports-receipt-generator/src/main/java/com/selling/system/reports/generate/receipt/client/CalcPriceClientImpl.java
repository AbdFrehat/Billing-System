package com.selling.system.reports.generate.receipt.client;

import com.selling.system.reports.generate.receipt.models.responses.CalcPriceResponse;
import com.orderizer.core.models.entities.Item;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class CalcPriceClientImpl implements CalcPriceClient {

    private final WebClient webClient;

    public CalcPriceClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://REPORTS-CALC-PRICE/selling/reports/calc/price/v1/").build();
    }

    @Override
    public Mono<CalcPriceResponse> retrieveCalcPrice(List<Item> items) {
        return webClient
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Flux.fromIterable(items), Item.class)
                .retrieve()
                .bodyToMono(CalcPriceResponse.class);
    }
}
