package com.selling.system.fetch.customer.controllers;

import com.selling.system.fetch.customer.services.CustomerService;
import com.selling.system.shared.module.models.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{saleId}")
    public Mono<Customer> getCustomer(@PathVariable @Valid String saleId) {
        return customerService.getCustomer(saleId);
    }


}
