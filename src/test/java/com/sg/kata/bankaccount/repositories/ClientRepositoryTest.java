package com.sg.kata.bankaccount.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sg.kata.bankaccount.entities.Customer;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    private static Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setLastName("ELASSAASSI");
    }

    @Test
    public void saveCustomerAndFindById() {
        Customer savedCustomer = this.customerRepository.save(customer);
        Customer foundCustomer = this.customerRepository.findById(savedCustomer.getId()).orElse(null);
        assertEquals(savedCustomer, foundCustomer);

    }

    @Test
    public void saveCustomerAndFindByName() {
        Customer savedCustomer = this.customerRepository.save(customer);
        List<Customer> customers = this.customerRepository.findByLastName(savedCustomer.getLastName());
        assertEquals(customers.get(0).getLastName(), "ELASSAASSI");
    }
}