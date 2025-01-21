package com.sg.kata.bankaccount.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sg.kata.bankaccount.entities.Customer;

/**
 * Customer Repository
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findById(int id);

	List<Customer> findByLastName(String name);
}
