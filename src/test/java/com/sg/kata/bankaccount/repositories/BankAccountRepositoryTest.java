package com.sg.kata.bankaccount.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sg.kata.bankaccount.entities.BankAccount;
import com.sg.kata.bankaccount.entities.Customer;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class BankAccountRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    private static Customer customer;
    private static BankAccount bankAccount;


    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setLastName("ELASSAASSI");

        bankAccount = new BankAccount();
        bankAccount.setBalance(990d);
    }

    @Test
    public void saveCustomerAndFindById() {
        Customer savedCustomer = this.customerRepository.save(customer);
        bankAccount.setCustomer(savedCustomer);
        BankAccount savedBankAccount = this.bankAccountRepository.save(bankAccount);
        assertEquals(savedBankAccount.getBalance(), 990d);
    }
}