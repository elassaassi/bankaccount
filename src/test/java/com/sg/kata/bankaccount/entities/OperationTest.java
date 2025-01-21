package com.sg.kata.bankaccount.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sg.kata.bankaccount.enums.OperationType;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class OperationTest {

    @Autowired
    private TestEntityManager entityManager;

    private BankAccount bankAccount;
    private Customer customer;
    private Operation operation;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setLastName("ELASSAASSI");

        bankAccount = new BankAccount();

        operation = new Operation(OperationType.DEPOSIT, 100d);
    }

    @Test
    public void saveOperation() {
        Customer savedCustomer = this.entityManager.persistAndFlush(customer);
        bankAccount.setCustomer(savedCustomer);
        BankAccount savedBankAccount = this.entityManager.persistAndFlush(bankAccount);
        assertEquals(savedBankAccount.getBalance(), 0d);

        operation.setBankAccount(savedBankAccount);
        Operation savedOperation = this.entityManager.persistAndFlush(operation);
        assertEquals(savedOperation.getType(), OperationType.DEPOSIT);
        assertEquals(savedOperation.getAmount(), 100d);
    }

  

}