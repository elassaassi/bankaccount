package com.sg.kata.bankaccount.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.kata.bankaccount.entities.Operation;

/**
 * Operation Repository
 */
@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
