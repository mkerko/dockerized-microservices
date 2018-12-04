package com.test.store.service;

import com.test.store.domain.Customer;
import com.test.store.exception.EntityWasNotFoundException;
import com.test.store.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer retrieveCustomerById(long id) throws EntityWasNotFoundException {
        log.debug("retrieveCustomerById has been called with id = " + id);
        Optional<Customer> customerOptional = customerRepository.findById(id);
        customerOptional.orElseThrow(() -> new EntityWasNotFoundException("Could not find customerOptional with id = " + id));
        return customerOptional.get();
    }
}
