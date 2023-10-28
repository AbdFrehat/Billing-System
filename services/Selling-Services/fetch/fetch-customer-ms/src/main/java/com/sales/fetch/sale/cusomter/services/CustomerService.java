package com.sales.fetch.sale.cusomter.services;

import com.sales.fetch.sale.cusomter.models.entites.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


public interface CustomerService {

    Mono<Customer> getCustomer(String saleId);
}
