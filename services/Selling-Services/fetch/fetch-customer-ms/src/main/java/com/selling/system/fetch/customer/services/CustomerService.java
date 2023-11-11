package com.selling.system.fetch.customer.services;

import com.selling.system.fetch.customer.models.entites.Customer;
import reactor.core.publisher.Mono;


public interface CustomerService {

    Mono<Customer> getCustomer(String saleId);
}
