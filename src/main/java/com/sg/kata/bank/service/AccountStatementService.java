/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.service;

import java.time.LocalDateTime;
import java.util.List;

import com.sg.kata.bank.model.OperationHistory;

/**
 * Account Statement Service
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 21:27:24
 */
public interface AccountStatementService {
	
	/**
	 * Method used to retrieve Account Operation History From a start Date Time
	 * @param accountNumber	Account Number
	 * @param start	Start Date Time
	 * @return	Account Statement Datas (To Use for OLTP reporting)
	 */
	public List<OperationHistory> accountStatement(String accountNumber, LocalDateTime start);

	/**
	 * Method used to get String formatted Account Operation History From a start Date Time
	 * @param accountNumber	Account Number
	 * @param start	Start Date Time
	 * @return	String Formatted Account Statement
	 */
	public String printAccountStatement(String accountNumber, LocalDateTime start);
}
