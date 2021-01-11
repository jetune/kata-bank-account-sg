/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sg.kata.bank.dao.AccountDao;
import com.sg.kata.bank.exception.BankAccountException;
import com.sg.kata.bank.model.Account;

/**
 * Account Data Access Object (In Memory) Implementation
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 22:40:03
 */
@Repository
public class InMemoryMapAccountDaoImpl implements AccountDao {
	
	/**
	 * In Memory Account Data Store
	 */
	private Map<String, Account> accounts = new HashMap<>();
	
	/* (non-Javadoc)
	 * @see com.sg.kata.bank.dao.AccountDao#save(com.sg.kata.bank.model.Account)
	 */
	@Override
	public synchronized Account save(Account account) {
		
		// if Account is null
		if(Objects.isNull(account)) {
			
			// Throw Exception
			throw new BankAccountException("SAVE_ACCOUNT_INVALID_ACCOUNT_PARAMETER", "Invalid Account");
		}
		
		// if Account Number is null
		if(StringUtils.isBlank(account.getNumber())) {
			
			// Throw Exception
			throw new BankAccountException("SAVE_ACCOUNT_INVALID_ACCOUNT_NUMBER", "Invalid Account Number");
		}
		
		// if Account Client is null
		if(StringUtils.isBlank(account.getClientId())) {
			
			// Throw Exception
			throw new BankAccountException("SAVE_ACCOUNT_INVALID_ACCOUNT_OWNER", "Invalid Account Owner");
		}
		
		// If Account Already Exists
		if(accounts.containsKey(account.getNumber())) {
			
			// Throw Exception
			throw new BankAccountException("SAVE_ACCOUNT_ALREADY_EXISTS", "Account Already Exists");
		}
		
		// Save in MAP
		accounts.put(account.getNumber(), account);
		
		// Return account
		return account;
	}

	/* (non-Javadoc)
	 * @see com.sg.kata.bank.dao.AccountDao#update(com.sg.kata.bank.model.Account)
	 */
	@Override
	public synchronized Account update(Account account) {

		// if Account is null
		if(Objects.isNull(account)) {
			
			// Throw Exception
			throw new BankAccountException("UPDATE_ACCOUNT_INVALID_ACCOUNT_PARAMETER", "Invalid Account");
		}
		
		// if Account Number is null
		if(StringUtils.isBlank(account.getNumber())) {
			
			// Throw Exception
			throw new BankAccountException("UPDATE_ACCOUNT_INVALID_ACCOUNT_NUMBER", "Invalid Account Number");
		}
		
		// If Account Dont Exists
		if(!accounts.containsKey(account.getNumber())) {
			
			// Throw Exception
			throw new BankAccountException("UPDATE_ACCOUNT_NOT_FOUND", "Account Not Found");
		}
		
		// Update Account
		accounts.put(account.getNumber(), account);
		
		// Return updated account
		return account;
	}
	
	/* (non-Javadoc)
	 * @see com.sg.kata.bank.dao.AccountDao#findByNumber(java.lang.String)
	 */
	@Override
	public synchronized Account findByNumber(String accountNumber) {
		
		// if Account Number is BlanK
		if(StringUtils.isBlank(accountNumber)) {
			
			// Return all Clients
			throw new BankAccountException("FIND_ACCOUNT_INVALID_ACCOUNT_NUMBER", "Invalid Account Number");
		}
		
		// Get the Client
		Account account = accounts.get(accountNumber);
		
		// If Client Is Null
		if(Objects.isNull(account)) {
			
			// Return all Clients
			throw new BankAccountException("FIND_ACCOUNT_NOT_FOUND", "Account ID Not Found");
		}
		
		// Find and return Account
		return account;
	}
}
