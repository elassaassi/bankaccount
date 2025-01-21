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
import com.sg.kata.bankaccount.entities.Operation;
import com.sg.kata.bankaccount.enums.OperationType;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class OperationRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private OperationRepository operationRepository;

    private static Customer customer;
    private static BankAccount bankAccount;
    private static Operation operation;


    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setLastName("ELASSAASSI");

        bankAccount = new BankAccount();
        bankAccount.setBalance(990d);

        operation = new Operation(OperationType.DEPOSIT, 100d);
    }

    @Test
    public void saveCustomerAndFindById() {
        Customer savedCustomer = this.customerRepository.save(customer);
        bankAccount.setCustomer(savedCustomer);
        BankAccount savedBankAccount = this.bankAccountRepository.save(bankAccount);
        assertEquals(savedBankAccount.getBalance(), 990d);

        operation.setBankAccount(savedBankAccount);
        Operation savedOperation = this.operationRepository.save(operation);
        assertEquals(savedOperation.getType(), OperationType.DEPOSIT);
        assertEquals(savedOperation.getAmount(), 100d);
    }
}