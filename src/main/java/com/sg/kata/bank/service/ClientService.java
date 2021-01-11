/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.service;

import com.sg.kata.bank.model.Client;

/**
 * Classe 
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 21:52:58
 */
public interface ClientService {
	
	/**
	 * Method used to Create Client in datastore
	 * @param client	Client to save
	 * @return	Saved Client
	 */
	public Client save(Client client);
	
	/**
	 * Method used to retrieve a Client by Client ID
	 * @param clientId Client ID
	 * @return	Founded Account
	 */
	public Client findById(String clientId);
}
