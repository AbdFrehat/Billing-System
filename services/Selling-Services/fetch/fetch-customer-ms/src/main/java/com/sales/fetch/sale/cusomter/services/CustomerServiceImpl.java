package com.sales.fetch.sale.cusomter.services;

import com.sales.fetch.sale.cusomter.models.entites.Customer;
import com.sales.fetch.sale.cusomter.models.entites.Sale;
import com.sales.fetch.sale.cusomter.repositories.CustomerRepository;
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
