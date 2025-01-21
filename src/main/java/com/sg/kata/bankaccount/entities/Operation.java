package com.sg.kata.bankaccount.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.sg.kata.bankaccount.enums.OperationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

/**
 * Operation Entity
 */
@Entity
public class Operation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'DEPOSIT'")
	@Enumerated(EnumType.STRING)
	private OperationType type;

	@NotNull(message = "Amount is required")
	private Double amount;

	private LocalDateTime date;

	@NotNull(message = "Bank account is required")
	@ManyToOne
	private BankAccount bankAccount;

	public Operation() {
		this.date = LocalDateTime.now();
	}

	/**
	 * Init of a new operation
	 * 
	 * @param type   : DEBIT / WITHDRAWAL
	 * @param amount : Amount of operation
	 */
	public Operation(OperationType type, Double amount) {
		this.type = type;
		this.amount = amount;
		this.date = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		return "Operation{" + "id=" + id + ", type=" + type + ", amount=" + amount + ", date=" + date.format(formatter)
				+ "}";
	}

}
