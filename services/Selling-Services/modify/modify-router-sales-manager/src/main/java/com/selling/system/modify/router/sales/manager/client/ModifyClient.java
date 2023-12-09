package com.selling.system.modify.router.sales.manager.client;

import com.selling.system.shared.module.models.entities.Sale;
import com.selling.system.shared.module.models.enums.CommandType;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ModifyClient {

    Mono<ResponseEntity<Void>> sendModifyCommand(Sale sale, CommandType commandType);

    Mono<ResponseEntity<Void>> sendMultiModifyCommand(List<Sale> sales, CommandType commandType);

}
