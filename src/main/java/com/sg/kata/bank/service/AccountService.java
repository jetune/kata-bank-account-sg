/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.service;

import com.sg.kata.bank.model.Account;

/**
 * Account Service Interface
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 20:21:26
 */
public interface AccountService {
	
	/**
	 * Method used to create a bank account in datastore
	 * @param account	Bank Account To create
	 * @return	Created Bank Account
	 */
	public Account save(Account account);
	
	/**
	 * Method used to update bank account
	 * @param account	Bank Account to Update
	 * @return	Updated Bank Account
	 */
	public Account update(Account account);
	
	/**
	 * Method used to retrieve an Account by Account Number
	 * @param accountNumber	Account Number
	 * @return	Founded Account
	 */
	public Account findByNumber(String accountNumber);
}
