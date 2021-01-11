/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.service.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.sg.kata.bank.dao.AccountDao;
import com.sg.kata.bank.dao.OperationDao;
import com.sg.kata.bank.event.DepositOperationEvent;
import com.sg.kata.bank.event.WithDrawalOperationEvent;
import com.sg.kata.bank.exception.BankAccountException;
import com.sg.kata.bank.model.Account;
import com.sg.kata.bank.model.Operation;
import com.sg.kata.bank.model.constants.OperationType;
import com.sg.kata.bank.service.OperationService;

/**
 * Operation Service Implementation
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 00:35:31
 */
@Service
public class OperationServiceImpl implements OperationService {
	
	/**
	 * Operation DAO
	 */
	@Autowired
	private OperationDao operationDao;
	
	/**
	 * Account DAO
	 */
	@Autowired
	private AccountDao accountDao;
	
	/**
	 * Event Publisher
	 */
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	/* (non-Javadoc)
	 * @see com.sg.kata.bank.service.OperationService#deposit(java.lang.String, float)
	 */
	@Override
	public Boolean deposit(String accountNumber, float amount) {
		
		// if account number is not set
		if(amount <= 0) {
			
			// Throw exception
			throw new BankAccountException("OPERATION_INVALID_AMOUNT", "Invalid Operation Amount");
		}
		
		// Check preconditions
		Account targetAccount = precheck(accountNumber);
		
		// Build Operation
		Operation operation = Operation.builder()
				.accountNumber(targetAccount.getNumber())
				.amount(amount)
				.type(OperationType.DEPOSIT)
				.timestamp(LocalDateTime.now())
				.build();
		
		// Save Operation
		operationDao.save(operation);
		
		// Push Event
		eventPublisher.publishEvent(new DepositOperationEvent(this, operation));
		
		// Return true
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sg.kata.bank.service.OperationService#withdrawal(java.lang.String, float)
	 */
	@Override
	public Boolean withdrawal(String accountNumber, float amount) {
		
		// if account number is not set
		if(amount <= 0) {
			
			// Throw exception
			throw new BankAccountException("OPERATION_INVALID_AMOUNT", "Invalid Operation Amount");
		}
		
		// Check preconditions
		Account targetAccount = precheck(accountNumber);
		
		// Build Operation
		Operation operation = Operation.builder()
				.accountNumber(targetAccount.getNumber())
				.amount(amount)
				.type(OperationType.WITHDRAWAL)
				.timestamp(LocalDateTime.now())
				.build();
		
		if(amount > targetAccount.getBalance()) {
			
			// Throw exception
			throw new BankAccountException("OPERATION_INSUFFICIENT_FUND", "Insufficiant funds");
		}
		
		// Save Operation
		operationDao.save(operation);
		
		// Push Event
		eventPublisher.publishEvent(new WithDrawalOperationEvent(this, operation));
		
		// Return true
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sg.kata.bank.service.OperationService#withdrawalFull(java.lang.String)
	 */
	@Override
	public Boolean withdrawalFull(String accountNumber) {

		// Check preconditions
		Account targetAccount = precheck(accountNumber);
		
		// Get the account balance
		float amount = targetAccount.getBalance();

		// Build Operation
		Operation operation = Operation.builder()
				.accountNumber(targetAccount.getNumber())
				.amount(amount)
				.type(OperationType.WITHDRAWAL)
				.timestamp(LocalDateTime.now())
				.build();
		
		// Save Operation
		operationDao.save(operation);
		
		// Push Event
		eventPublisher.publishEvent(new WithDrawalOperationEvent(this, operation));
		
		// Return true
		return true;
	}
	
	/**
	 * Method used to precheck parameters before operation
	 * @param accountNumber	Account Number
	 * @return Target Account
	 */
	private Account precheck(String accountNumber) {
		
		// if account number is not set
		if(StringUtils.isBlank(accountNumber)) {
			
			// Throw exception
			throw new BankAccountException("OPERATION_INVALID_ACCOUNT_NUMBER", "Invalid Account Number");
		}
		
		// Find the Target Account
		Account account = accountDao.findByNumber(accountNumber);
		
		// If Account dont exists
		if(Objects.isNull(account)) {
			
			// Throw exception
			throw new BankAccountException("OPERATION_ACCOUNT_NOT_FOUND", "Account Not Found");
		}
		
		// Return the target Account
		return account;
	}
}
