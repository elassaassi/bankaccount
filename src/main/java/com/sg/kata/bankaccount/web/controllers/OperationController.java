package com.sg.kata.bankaccount.web.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sg.kata.bankaccount.config.SwaggerConfig;
import com.sg.kata.bankaccount.dtos.OperationDTO;
import com.sg.kata.bankaccount.dtos.OperationRequestBody;
import com.sg.kata.bankaccount.services.IOperationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Rest API exposing 3 endpoints To manage a Bank Account
 * 
 * 
 */
@Tag(name = SwaggerConfig.BANK_ACCOUNT, description = "Manage your bank account operations such as deposits, withdrawals, and balance viewing.")
@RestController
public class OperationController {

	private final IOperationService operationService;

	@Autowired
	public OperationController(IOperationService operationService) {
		this.operationService = operationService;
	}

	/**
	 * Balance Endpoint to get balance of a current bank account
	 * 
	 * @return balance amount
	 */
	@Operation(summary = "Get the balance of your bank account")
	@GetMapping("/balance")
	public Double getBalance() {
		return operationService.getBalance();
	}

	/**
	 * Deposit Endpoint to Add an amount to balance
	 * 
	 * @param operationRequestBody
	 * @return OperationDTO
	 */
	@Operation(summary = "Make a deposit to your bank account")
	@PostMapping("/deposit")
	public ResponseEntity<OperationDTO> deposit(@RequestBody OperationRequestBody operationRequestBody) {
		OperationDTO newOperation = operationService.deposit(operationRequestBody.getAmount());

		if (newOperation == null) {
			return ResponseEntity.noContent().build();
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newOperation.getId()).toUri();

		return ResponseEntity.created(location).body(newOperation);
	}

	/**
	 * Withdrawal Endpoint to withdraw an amount for the current bank account
	 * 
	 * @param operationRequestBody
	 * @return OperationDTO
	 */
	@Operation(summary = "Make a withdrawal from your bank account")
	@PostMapping("/withdraw")
	public ResponseEntity<OperationDTO> withdraw(@RequestBody OperationRequestBody operationRequestBody) {
		OperationDTO newOperation = operationService.withdraw(operationRequestBody.getAmount());

		if (newOperation == null) {
			return ResponseEntity.noContent().build();
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newOperation.getId()).toUri();

		return ResponseEntity.created(location).body(newOperation);
	}
}
