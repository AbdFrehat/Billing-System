package com.selling.system.modify.router.sales.manager.controller;

import com.selling.system.modify.router.sales.manager.client.ModifyClient;
import com.selling.system.shared.module.models.entities.Sale;
import com.selling.system.shared.module.models.enums.CommandType;
import jakarta.ws.rs.QueryParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ModifyRouterController {

    private final ModifyClient modifyClient;

    public ModifyRouterController(ModifyClient modifyClient) {
        this.modifyClient = modifyClient;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> sendModifyCommand(@RequestBody Sale sale, @RequestParam("command-type") CommandType commandType) {
        return modifyClient.sendModifyCommand(sale, commandType);
    }

    @PostMapping("/multi")
    public Mono<ResponseEntity<Void>> sendModifyCommands(@RequestBody List<Sale> sales,
                                                         @RequestParam("command-type") CommandType commandType) {
        return modifyClient.sendMultiModifyCommand(sales, commandType);
    }

}
