package com.selling.system.fetch.customer.services;

import com.selling.system.shared.module.models.entities.Customer;
import reactor.core.publisher.Mono;


public interface CustomerService {

    Mono<Customer> getCustomer(String saleId);
}
