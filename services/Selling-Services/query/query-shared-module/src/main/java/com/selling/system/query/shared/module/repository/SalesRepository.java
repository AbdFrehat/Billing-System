package com.selling.system.query.shared.module.repository;

import com.selling.system.query.shared.module.entites.Sale;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

public interface SalesRepository {

    Flux<Sale> getSales(Query query);

}
