package com.sg.kata.bankaccount.dtos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import com.sg.kata.bankaccount.entities.BankAccount;
import com.sg.kata.bankaccount.entities.Customer;
import com.sg.kata.bankaccount.entities.Operation;
import com.sg.kata.bankaccount.enums.OperationType;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationDtoTest {
    private ModelMapper modelMapper = new ModelMapper();

    private static Operation operation;
    private static OperationDTO operationDto;

    @BeforeAll
    public static void setUp() {
        Customer customer = new Customer();
        customer.setLastName("ELASSAASSI");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setCustomer(customer);
        bankAccount.setBalance(1000d);

        operation = new Operation();
        operation.setBankAccount(bankAccount);
        operation.setType(OperationType.DEPOSIT);
        operation.setAmount(100d);

        operationDto = new OperationDTO();
        operationDto.setBankAccountCustomerLastName("ELASSAASSI");
        operationDto.setBankAccountBalance(500d);
        operationDto.setType(OperationType.WITHDRAWAL);
        operationDto.setAmount(-100d);
        operationDto.setDate(LocalDateTime.now());
    }

    @Test
    public void mapOperationEntityToOperationDto() {
        OperationDTO dto = modelMapper.map(operation, OperationDTO.class);
        assertEquals(operation.getType(), dto.getType());
        assertEquals(operation.getAmount(), dto.getAmount());
        assertEquals(operation.getBankAccount().getBalance(), dto.getBankAccountBalance());
        assertEquals(operation.getBankAccount().getCustomer().getLastName(), dto.getBankAccountCustomerLastName());
    }

    @Test
    public void mapOperationDtoToOperationEntity() {
        Operation op = modelMapper.map(operationDto, Operation.class);
        assertEquals(op.getType(), operationDto.getType());
        assertEquals(op.getAmount(), operationDto.getAmount());
        assertEquals(op.getBankAccount().getBalance(), operationDto.getBankAccountBalance());
        assertEquals(op.getBankAccount().getCustomer().getLastName(), operationDto.getBankAccountCustomerLastName());
    }

}