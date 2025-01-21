package com.sg.kata.bankaccount.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sg.kata.bankaccount.enums.OperationType;

/**
 * Operation DTO to be returned as a Response to Customer
 */
public class OperationDTO {
	private int id;
	private OperationType type;

	private Double amount;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime date;

	@JsonProperty("balance")
	private Double bankAccountBalance;

	@JsonProperty("customer")
	private String bankAccountCustomerLastName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OperationType getType() {
		return type;
	}

	public void setType(OperationType type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Double getBankAccountBalance() {
		return bankAccountBalance;
	}

	public void setBankAccountBalance(Double bankAccountBalance) {
		this.bankAccountBalance = bankAccountBalance;
	}

	public String getBankAccountCustomerLastName() {
		return bankAccountCustomerLastName;
	}

	public void setBankAccountCustomerLastName(String bankAccountCustomerLastName) {
		this.bankAccountCustomerLastName = bankAccountCustomerLastName;
	}

	public OperationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OperationDTO(int id, OperationType type, Double amount, LocalDateTime date, Double bankAccountBalance,
			String bankAccountCustomerLastName) {
		super();
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.bankAccountBalance = bankAccountBalance;
		this.bankAccountCustomerLastName = bankAccountCustomerLastName;
	}
}
