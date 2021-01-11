/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sg.kata.bank.dao.OperationDao;
import com.sg.kata.bank.exception.BankAccountException;
import com.sg.kata.bank.model.Operation;

/**
 * Operation Data Access Object (In Memory) Implementation
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 22:55:46
 */
@Repository
public class InMemoryMapOperationDaoImpl implements OperationDao {
	
	/**
	 * In Memory Operations Store
	 */
	private Map<String, List<Operation>> operations = new HashMap<>();
	
	/* (non-Javadoc)
	 * @see com.sg.kata.bank.dao.OperationDao#save(com.sg.kata.bank.model.Operation)
	 */
	@Override
	public synchronized Operation save(Operation operation) {
		
		// if Operation is null
		if(Objects.isNull(operation)) {
			
			// Throw Exception
			throw new BankAccountException("SAVE_OPERATION_INVALID_OPERATION", "Invalid Operation");
		}
		
		// if Operation is account is invalid
		if(StringUtils.isBlank(operation.getAccountNumber())) {
			
			// Throw Exception
			throw new BankAccountException("SAVE_OPERATION_INVALID_OPERATION_ACCOUNT", "Invalid Operation Account");
		}
		
		// if Operation date is null
		if(Objects.isNull(operation.getTimestamp())) {
			
			// Throw Exception
			throw new BankAccountException("SAVE_OPERATION_INVALID_OPERATION_TIMESPAMP", "Invalid Operation Timestamp");
		}
		
		// Get The Current Account Operation
		List<Operation> accountOperations = operations.get(operation.getAccountNumber());
		
		// If No Operations
		if(CollectionUtils.isEmpty(accountOperations)) {
			
			// Initialize List
			accountOperations = new ArrayList<>();
		}
		
		// Add The current Operation
		accountOperations.add(operation);
		
		// Put The updated List in the Map
		operations.put(operation.getAccountNumber(), accountOperations);
		
		// Return registered Operation
		return operation;
	}

	/* (non-Javadoc)
	 * @see com.sg.kata.bank.dao.OperationDao#findByAccountNumber(java.lang.String)
	 */
	@Override
	public synchronized List<Operation> findByAccountNumber(String accountNumber) {
		
		// if Account Number is BlanK
		if(StringUtils.isBlank(accountNumber)) {
			
			// Return all Clients
			throw new BankAccountException("FIND_OPERATIONS_INVALID_ACCOUNT_NUMBER", "Invalid Account Number");
		}
		
		// Return the operations list
		return operations.get(accountNumber);
	}
}
