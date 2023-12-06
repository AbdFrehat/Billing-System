package com.selling.system.reports.generate.receipt.client;

import com.selling.system.shared.module.models.commands.QueryCommand;
import com.selling.system.shared.module.models.commands.QueryField;
import com.selling.system.shared.module.models.entities.Sale;
import com.selling.system.shared.module.models.enums.FieldType;
import com.selling.system.shared.module.models.enums.QueryMethod;
import com.selling.system.shared.module.models.responses.QueryResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

import static com.selling.system.shared.module.convertors.ObjectToSalesConvertor.toSales;

@Component
public class DataManagerClientImpl implements DataManagerClient {

    private final WebClient webClient;

    public DataManagerClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Mono<Sale> retrieveSale(String saleId) {
        return webClient
                .post()
                .uri("http://DATA-MANAGER-MS/selling/data/manager/sale/v1/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(buildQueryCommand(saleId))
                .retrieve()
                .bodyToMono(QueryResponse.class)
                .map(queryResponse -> toSales(queryResponse.getData()).get(0));
    }

    private QueryCommand buildQueryCommand(String saleId) {
        return QueryCommand
                .builder()
                .queryFields(Map.of("F1", QueryField
                        .builder()
                        .field("id")
                        .value(saleId)
                        .fieldType(FieldType.OTHER)
                        .build()))
                .queryMethod(QueryMethod.GET_SALES)
                .build();
    }
}
