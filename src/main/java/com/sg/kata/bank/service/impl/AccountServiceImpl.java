/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sg.kata.bank.dao.AccountDao;
import com.sg.kata.bank.model.Account;
import com.sg.kata.bank.service.AccountService;

/**
 * Account Service Implementation
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 00:30:51
 */
public class AccountServiceImpl implements AccountService {
	
	/**
	 * Account DAO
	 */
	@Autowired
	private AccountDao accountDao;
	
	/* (non-Javadoc)
	 * @see com.sg.kata.bank.service.AccountService#save(com.sg.kata.bank.model.Account)
	 */
	@Override
	public Account save(Account account) {
		
		// Save and return account
		return accountDao.save(account);
	}

	/* (non-Javadoc)
	 * @see com.sg.kata.bank.service.AccountService#update(com.sg.kata.bank.model.Account)
	 */
	@Override
	public Account update(Account account) {
		
		// Update and return account
		return accountDao.update(account);
	}
	
	/* (non-Javadoc)
	 * @see com.sg.kata.bank.service.AccountService#findByNumber(java.lang.String)
	 */
	@Override
	public Account findByNumber(String accountNumber) {
		
		// Find and return Account
		return accountDao.findByNumber(accountNumber);
	}
}
