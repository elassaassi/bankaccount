package com.sg.kata.bankaccount.services.impl;

import com.sg.kata.bankaccount.dtos.OperationDTO;
import com.sg.kata.bankaccount.entities.BankAccount;
import com.sg.kata.bankaccount.entities.Operation;
import com.sg.kata.bankaccount.repositories.BankAccountRepository;
import com.sg.kata.bankaccount.repositories.OperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Active Mockito pour les tests
class OperationServiceImplTest {

    @Mock
    private OperationRepository operationRepository;

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private  ModelMapper modelMapper;

    @InjectMocks
    private OperationServiceImpl operationService;

    private BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        bankAccount = new BankAccount();
        bankAccount.setId(1L);
        bankAccount.setBalance(1000.0);
    }

    @Test
    void testDeposit() {
        when(bankAccountRepository.findById(1L)).thenReturn(Optional.of(bankAccount));
        when(operationRepository.save(any(Operation.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(modelMapper.map(any(Operation.class), eq(OperationDTO.class))).thenReturn(new OperationDTO());

        OperationDTO result = operationService.deposit(500.0);

        assertNotNull(result);
        assertEquals(1500.0, bankAccount.getBalance());
        verify(bankAccountRepository).save(bankAccount);
        verify(operationRepository).save(any(Operation.class));
    }

    @Test
    void testWithdraw_Success() {
        when(bankAccountRepository.findById(1L)).thenReturn(Optional.of(bankAccount));
        when(operationRepository.save(any(Operation.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(modelMapper.map(any(Operation.class), eq(OperationDTO.class))).thenReturn(new OperationDTO());

        OperationDTO result = operationService.withdraw(200.0);

        assertNotNull(result);
        assertEquals(800.0, bankAccount.getBalance());
        verify(bankAccountRepository).save(bankAccount);
        verify(operationRepository).save(any(Operation.class));
    }

    @Test
    void testWithdraw_ThrowsException_WhenAmountIsZeroOrNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> operationService.withdraw(0.0));
        assertEquals("Amount must be greater than zero", exception.getMessage());
    }

    @Test
    void testGetBalance_Success() {
        when(bankAccountRepository.findById(1L)).thenReturn(Optional.of(bankAccount));

        Double balance = operationService.getBalance();

        assertEquals(1000.0, balance);
    }

    @Test
    void testGetBalance_ThrowsException_WhenBankAccountNotFound() {
        when(bankAccountRepository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> operationService.getBalance());
        assertEquals("Bank account not found", exception.getMessage());
    }
}
