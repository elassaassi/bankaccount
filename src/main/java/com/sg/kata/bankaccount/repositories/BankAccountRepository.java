package com.sg.kata.bankaccount.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.kata.bankaccount.entities.BankAccount;

/**
 * Bank Account Repository
 */
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

	Optional<BankAccount> findById(int id);
}
