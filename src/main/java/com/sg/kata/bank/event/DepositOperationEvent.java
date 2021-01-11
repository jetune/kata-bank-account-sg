/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.event;

import com.sg.kata.bank.model.Operation;

/**
 * Deposit Operation Event
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 00:25:36
 */
public class DepositOperationEvent extends BaseEvent<Operation> {

	/**
	 * Eclipse generated ID
	 */
	private static final long serialVersionUID = 758631953948534138L;
	
	/**
	 * Constructor with parameters 
	 * @param source Event Source
	 */
	public DepositOperationEvent(Object source, Operation payload) {
		
		// Parent call
		super(source, payload);
	}
}
