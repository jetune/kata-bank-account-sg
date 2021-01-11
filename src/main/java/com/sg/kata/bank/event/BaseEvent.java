/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

/**
 * Application Base Event
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 00:22:28
 */
@Getter
public class BaseEvent<T> extends ApplicationEvent {
	
	/**
	 * Eclipse Generated ID
	 */
	private static final long serialVersionUID = 6493362819035471822L;
	
	/**
	 * Event Payload
	 */
	private transient T payload;
	
	/**
	 * Constructor with parameters 
	 * @param source Event Source
	 */
	public BaseEvent(Object source, T payload) {
		
		// Parent call
		super(source);
		
		// Initialize payload
		this.payload = payload;
	}
}
