/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.sg.kata.bank.dao.AccountDao;
import com.sg.kata.bank.dao.AccountStatementDao;
import com.sg.kata.bank.event.DepositOperationEvent;
import com.sg.kata.bank.event.WithDrawalOperationEvent;
import com.sg.kata.bank.model.Account;
import com.sg.kata.bank.model.Operation;
import com.sg.kata.bank.model.OperationHistory;

/**
 * Bank Account Event Listener
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 01:35:57
 */
@Component
public class BankAccountListener {
	
	/**
	 * Account Statement DAO
	 */
	@Autowired
	private AccountStatementDao accountStatementDao;
	
	/**
	 * Account DAO
	 */
	@Autowired
	private AccountDao accountDao;
	
	/**
	 * Method used to handle DepositOperationEvent
	 * @param event	Source event
	 */
	@EventListener
	public void depositOperationEventListener(DepositOperationEvent event) {
		
		// Get the Operation
		Operation operation = event.getPayload();
		
		// Find the Target Account
		Account account = accountDao.findByNumber(operation.getAccountNumber());
		
		// Add the Operation Ammount to the Balance
		account.setBalance(account.getBalance() + operation.getAmount());
		
		// Update the Account
		account = accountDao.update(account);
		
		// Add Operation History
		accountStatementDao.addOperationHistory(
			OperationHistory.builder()
			.operation(operation)
			.accountBalance(account.getBalance())
			.build()
		);
	}
	
	/**
	 * Method used to handle WithdrawOperationEvent
	 * @param event	Source event
	 */
	@EventListener
	public void withDrawalOperationEventListener(WithDrawalOperationEvent event) {
		
		// Get the Operation
		Operation operation = event.getPayload();
		
		// Find the Target Account
		Account account = accountDao.findByNumber(operation.getAccountNumber());
		
		// Substract the Operation Ammount from the Balance
		account.setBalance(account.getBalance() - operation.getAmount());
		
		// Update the Account
		account = accountDao.update(account);
		
		// Add Operation History
		accountStatementDao.addOperationHistory(
			OperationHistory.builder()
			.operation(operation)
			.accountBalance(account.getBalance())
			.build()
		);
	}
}
