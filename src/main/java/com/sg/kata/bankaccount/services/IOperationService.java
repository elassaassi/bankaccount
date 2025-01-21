package com.sg.kata.bankaccount.services;

import com.sg.kata.bankaccount.dtos.OperationDTO;

/**
 * Interface to call OperationService ; Port 
 */
public interface IOperationService {
	
    OperationDTO deposit(Double amount);

    OperationDTO withdraw(Double amount);
    
    Double getBalance() ;
}
