/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sg.kata.bank.dao.AccountStatementDao;
import com.sg.kata.bank.exception.BankAccountException;
import com.sg.kata.bank.model.Operation;
import com.sg.kata.bank.model.OperationHistory;

/**
 * Operation History Data Access Object (In Memory) Implementation
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 23:35:56
 */
@Repository
public class InMemoryMapAccountStatementDaoImpl implements AccountStatementDao {
	
	/**
	 * In Memory Operations History Store
	 */
	private Map<String, List<OperationHistory>> histories = new HashMap<>();
	
	/* (non-Javadoc)
	 * @see com.sg.kata.bank.dao.AccountStatementDao#addOperationHistory(com.sg.kata.bank.model.OperationHistory)
	 */
	@Override
	public synchronized Boolean addOperationHistory(OperationHistory operationHistory) {
		
		// if Operation History is null
		if(Objects.isNull(operationHistory)) {
			
			// Throw Exception
			throw new BankAccountException("SAVE_OPERATION_HISTORY_INVALID_OPERATION", "Invalid History");
		}
		
		// Get Operation
		Operation operation = operationHistory.getOperation();

		// if Operation is null
		if(Objects.isNull(operation)) {
			
			// Throw Exception
			throw new BankAccountException("SAVE_OPERATION_HISTORY_INVALID_OPERATION", "Invalid History Operation");
		}
		
		// if Operation is account is invalid
		if(StringUtils.isBlank(operation.getAccountNumber())) {
			
			// Throw Exception
			throw new BankAccountException("SAVE_OPERATION_HISTORY_INVALID_OPERATION_ACCOUNT", "Invalid History Operation Account");
		}
		
		// if Operation date is null
		if(Objects.isNull(operation.getTimestamp())) {
			
			// Throw Exception
			throw new BankAccountException("SAVE_OPERATION_HISTORY_INVALID_OPERATION_TIMESPAMP", "Invalid History Operation Timestamp");
		}

		// Get The Current Account Operation Histories
		List<OperationHistory> accountHistories = histories.get(operation.getAccountNumber());
		
		// If No Operations
		if(CollectionUtils.isEmpty(accountHistories)) {
			
			// Initialize List
			accountHistories = new ArrayList<>();
		}
		
		// Add The current Operation History
		accountHistories.add(operationHistory);
		
		// Put The updated List in the Map
		histories.put(operation.getAccountNumber(), accountHistories);
		
		// Return registered Operation History
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sg.kata.bank.dao.AccountStatementDao#findByAcountNumberAndDate(java.lang.String, java.time.LocalDateTime)
	 */
	@Override
	public List<OperationHistory> findByAcountNumberAndDate(String accountNumber, LocalDateTime start) {
		
		// if Account Number is BlanK
		if(StringUtils.isBlank(accountNumber)) {
			
			// Return all Clients
			throw new BankAccountException("FIND_HISTORIES_INVALID_ACCOUNT_NUMBER", "Invalid Account Number");
		}
		
		// Local Start Date
		final LocalDateTime localStart = Objects.isNull(start) ? LocalDateTime.MIN : start;
		
		// Return Filtered History
		return histories.get(accountNumber).stream()
				.filter(Objects::nonNull)
				.filter(history -> Objects.nonNull(history.getOperation()))
				.filter(history -> Objects.nonNull(history.getOperation().getTimestamp()))
				.filter(history -> history.getOperation().getTimestamp().isAfter(localStart))
				.collect(Collectors.toList());
	}
}
