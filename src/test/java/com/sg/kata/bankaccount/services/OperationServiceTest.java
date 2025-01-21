package com.sg.kata.bankaccount.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.sg.kata.bankaccount.dtos.OperationDTO;
import com.sg.kata.bankaccount.entities.BankAccount;
import com.sg.kata.bankaccount.entities.Operation;
import com.sg.kata.bankaccount.repositories.BankAccountRepository;
import com.sg.kata.bankaccount.repositories.OperationRepository;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
class OperationServiceTest {

    @Autowired
    IOperationService operationService;

    @MockBean
    OperationRepository operationRepository;

    @MockBean
    BankAccountRepository bankAccountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(1000d);

        Operation operation = new Operation();
        operation.setBankAccount(bankAccount);

        when(bankAccountRepository.findById(any(Long.class)))
        .thenReturn(Optional.of(bankAccount));

        when(bankAccountRepository.save(any(BankAccount.class)))
                .thenReturn(bankAccount);

        when(operationRepository.save(any(Operation.class)))
                .thenReturn(operation);

    }

    @Test
    void deposit() {
        OperationDTO deposit = operationService.deposit(100d);
        assertNotNull(deposit);
        assertEquals(deposit.getBankAccountBalance(), 1100d);
    }

    @Test
    void withdraw() {
        OperationDTO withdraw = operationService.withdraw(100d);
        assertNotNull(withdraw);
        assertEquals(withdraw.getBankAccountBalance(), 900d);
    }
}
