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
class BankAccountTest {

    @Autowired
    private TestEntityManager entityManager;

    private BankAccount bankAccount;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setLastName("ELASSAASSI");

        bankAccount = new BankAccount();
        bankAccount.setBalance(990d);
    }

    @Test
    public void saveClient() {
        Customer savedCustomer = this.entityManager.persistAndFlush(customer);
        bankAccount.setCustomer(savedCustomer);
        BankAccount savedBankAccount = this.entityManager.persistAndFlush(bankAccount);
        assertEquals(savedBankAccount.getBalance(), 990d);
    }



    
    
}
