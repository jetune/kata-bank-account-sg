/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.kata.bank.dao.AccountStatementDao;
import com.sg.kata.bank.model.OperationHistory;
import com.sg.kata.bank.service.AccountStatementService;

/**
 * Account Statement Service Implementation
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 01:06:04
 */
@Service
public class AccountStatementServiceImpl implements AccountStatementService {
	
	/**
	 * Output Format
	 */
	private static final String ACCOUNT_STATEMENT_FORMAT = "%-15s\t%-15s\t%35s\t%15s";
	
	/**
	 * Account Statement DAO
	 */
	@Autowired
	private AccountStatementDao accountStatementDao;
	
	/* (non-Javadoc)
	 * @see com.sg.kata.bank.service.AccountStatementService#accountStatement(java.lang.String, java.time.LocalDateTime)
	 */
	@Override
	public List<OperationHistory> accountStatement(String accountNumber, LocalDateTime start) {
		
		// Find and return history
		return accountStatementDao.findByAcountNumberAndDate(accountNumber, start);
	}

	/* (non-Javadoc)
	 * @see com.sg.kata.bank.service.AccountStatementService#printAccountStatement(java.lang.String, java.time.LocalDateTime)
	 */
	@Override
	public String printAccountStatement(String accountNumber, LocalDateTime start) {
		
		// Get the Operations Histories
		List<OperationHistory> histories = accountStatementDao.findByAcountNumberAndDate(accountNumber, start);
		
		// Account Statement output
		String accountStatement = "";
		
		// Header
		String header = String.format(ACCOUNT_STATEMENT_FORMAT, "OPERATION", "AMOUNT", "DATE", "BALANCE");
		
		// If there are operations
		if(CollectionUtils.isNotEmpty(histories)) {
			
			// Build the output
			accountStatement = histories.stream()
					.map(history -> String.format(
							ACCOUNT_STATEMENT_FORMAT,
							history.getOperation().getType(),
							history.getOperation().getAmount(),
							history.getOperation().getTimestamp(),
							history.getAccountBalance())
					)
					.collect(Collectors.joining("\n"));
			
		}
		
		// Return the formatted Account Statement
		return header + "\n" + accountStatement;
	}
}
