/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.exception;

import lombok.Getter;

/**
 * Bank Account Base Exception
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 22:07:25
 */
@Getter
public class BankAccountException extends RuntimeException {

	/**
	 * Eclipse generated UID
	 */
	private static final long serialVersionUID = -6051635250070207220L;
	
	/**
	 * Default code
	 */
	public static final String GENERIC_CODE = "BANK_ACCOUNT_GENERIC_EXCEPTION";
	
	/**
	 * Default message
	 */
	public static final String GENERIC_MESSAGE = "Error occured when compute operation";
	
	/**
	 * Exception code
	 */
	private final String code;
	
	/**
	 * No argument Constructor 
	 */
	public BankAccountException() {
		
		// Initialize inherited properties
		super(GENERIC_MESSAGE);
		
		// Initialize exception code
		this.code = GENERIC_CODE;
	}

	/**
	 * Constructor with parameters
	 * @param cause Exception cause
	 */
	public BankAccountException(Throwable cause) {
		
		// Initialize inherited properties
		super(GENERIC_MESSAGE, cause);
		
		// Initialize exception code
		this.code = GENERIC_CODE;
	}
	
	/**
	 * Full argument Constructor  
	 * @param code	Exception code
	 * @param message	Exception message
	 * @param cause	Exception cause
	 */
	public BankAccountException(String code, String message, Throwable cause) {
		
		// Initialize inherited properties
		super(message, cause);
		
		// Initialize exception code
		this.code = code;
	}

	/**
	 * Full argument Constructor  
	 * @param code	Exception code
	 * @param message	Exception message
	 */
	public BankAccountException(String code, String message) {
		
		// Initialize inherited properties
		super(message);
		
		// Initialize exception code
		this.code = code;
	}
}
