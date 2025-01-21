package com.sg.kata.bankaccount.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerTest {

    @Autowired
    private TestEntityManager entityManager;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setLastName("ELASSAASSI");
    }

    @Test
    public void saveCustomer() {
        Customer savedCustomer = this.entityManager.persistAndFlush(customer);
        assertEquals(savedCustomer.getLastName(), "ELASSAASSI");
    }


}