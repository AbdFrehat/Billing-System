package com.sales.fetch.sale.cusomter.controllers;

import com.sales.fetch.sale.cusomter.models.entites.Customer;
import com.sales.fetch.sale.cusomter.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{saleId}")
    public Mono<Customer> getCustomer(@PathVariable @Valid String saleId) {
        return customerService.getCustomer(saleId);
    }


}
