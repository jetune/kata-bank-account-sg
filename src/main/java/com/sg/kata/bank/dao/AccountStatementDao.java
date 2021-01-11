/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.sg.kata.bank.model.OperationHistory;

/**
 * Account Statement Data Acces sObject
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 21:57:43
 */
public interface AccountStatementDao {
	
	/**
	 * Method used to Add an Operation History occured on an Account
	 * @param operationHistory	Operation History
	 * @return	Operation Status
	 */
	public Boolean addOperationHistory(OperationHistory operationHistory);
	
	/**
	 * Method used to retrieve Operation History By Account And Start Date from Data Store
	 * @param accountNumber	Target Account Number
	 * @param start	History Start Date
	 * @return	Operation History List
	 */
	public List<OperationHistory> findByAcountNumberAndDate(String accountNumber, LocalDateTime start);
}
