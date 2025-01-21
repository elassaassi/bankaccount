package com.sg.kata.bankaccount.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sg.kata.bankaccount.dtos.OperationDTO;
import com.sg.kata.bankaccount.entities.BankAccount;
import com.sg.kata.bankaccount.entities.Operation;
import com.sg.kata.bankaccount.enums.OperationType;
import com.sg.kata.bankaccount.repositories.BankAccountRepository;
import com.sg.kata.bankaccount.repositories.OperationRepository;
import com.sg.kata.bankaccount.services.IOperationService;

/**
 * Implementation of Operation Service ; Adapter
 */
@Service
public class OperationServiceImpl implements IOperationService {

	private final OperationRepository operationRepository;
	private final BankAccountRepository bankAccountRepository;
	private final ModelMapper modelMapper;

	public OperationServiceImpl(OperationRepository operationRepository, BankAccountRepository bankAccountRepository,
			ModelMapper modelMapper) {
		this.operationRepository = operationRepository;
		this.bankAccountRepository = bankAccountRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public OperationDTO deposit(Double amount) {
		BankAccount bankAccount = bankAccountRepository.findById(1L)
				.orElseThrow(() -> new IllegalArgumentException("Bank account not found"));

		Operation operation = new Operation(OperationType.DEPOSIT, amount);
		operation.setBankAccount(bankAccount);

		bankAccount.setBalance(bankAccount.getBalance() + amount);
		bankAccountRepository.save(bankAccount);

		Operation savedOperation = operationRepository.save(operation);
		return modelMapper.map(savedOperation, OperationDTO.class);
	}

	@Override
	public OperationDTO withdraw(Double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Amount must be greater than zero");
		}

		BankAccount bankAccount = bankAccountRepository.findById(1L)
				.orElseThrow(() -> new IllegalArgumentException("Bank account not found"));

		Double withdrawalAmount = -amount; // Ensuring negative value for withdrawal
		Operation operation = new Operation(OperationType.WITHDRAWAL, withdrawalAmount);
		operation.setBankAccount(bankAccount);

		bankAccount.setBalance(bankAccount.getBalance() + withdrawalAmount);
		bankAccountRepository.save(bankAccount);

		Operation savedOperation = operationRepository.save(operation);
		return modelMapper.map(savedOperation, OperationDTO.class);
	}

	@Override
	public Double getBalance() {
		return bankAccountRepository.findById(1L).map(BankAccount::getBalance)
				.orElseThrow(() -> new IllegalArgumentException("Bank account not found"));
	}
}
