/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.dao;

import java.util.List;

import com.sg.kata.bank.model.Operation;

/**
 * Account Operation DAO Interface
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 20:23:44
 */
public interface OperationDao {
	
	/**
	 * Method used to create a bank account operation (transaction) in datastore
	 * @param operation	Operation To create
	 * @return	Created Operation
	 */
	public Operation save(Operation operation);
	
	/**
	 * Method used to retrieve an Account by Account Number
	 * @param accountNumber	Account Number
	 * @return	Founded Account
	 */
	public List<Operation> findByAccountNumber(String accountNumber);
}
