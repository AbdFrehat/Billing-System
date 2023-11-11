package com.selling.system.fetch.customer.services;

import com.selling.system.fetch.customer.models.entites.Customer;
import com.selling.system.fetch.customer.models.entites.Sale;
import com.selling.system.fetch.customer.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Mono<Customer> getCustomer(String saleId) {
        return customerRepository.findCustomerBySaleId(saleId).map(Sale::getCustomer);
    }
}
