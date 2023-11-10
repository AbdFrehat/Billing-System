package com.sales.query.shared.models.repository;

import com.sales.query.shared.models.command.QueryCommand;
import com.sales.query.shared.models.entites.Sale;
import reactor.core.publisher.Flux;

public interface SalesRepository {

    Flux<Sale> getSales(QueryCommand queryCommand);

}
