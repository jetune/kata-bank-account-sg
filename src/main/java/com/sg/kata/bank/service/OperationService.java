/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.service;

/**
 * Operation Service Interface
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 20:57:07
 */
public interface OperationService {
	
	/**
	 * Method used to make a deposit operation in Bank Account
	 * @param accountNumber	Target Account Number
	 * @param amount	Deposit Operation Amount
	 * @return	Operation Status
	 */
	public Boolean deposit(String accountNumber, float amount);
	
	/**
	 * Method used to make a withdrawal operation in Bank Account
	 * @param accountNumber	Target Account Number
	 * @param amount	Withdrawal Operation Amount
	 * @return	Operation Status
	 */
	public Boolean withdrawal(String accountNumber, float amount);

	/**
	 * Method used to withdraw all my money from Bank Account
	 * @param accountNumber	Target Account Number
	 * @return	Operation Status
	 */
	public Boolean withdrawalFull(String accountNumber);
}
